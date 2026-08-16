addClassPath(".");
String MAIN_DIRECTORY = new File(getSourceFileInfo()).getParentFile().getAbsolutePath();
String LOG_FILE = MAIN_DIRECTORY + "/log.txt";
cd(MAIN_DIRECTORY);
addClassPath(".");
importCommands("lib");
importCommands("lib.file");
importCommands("lib.misc");
importCommands("lib.activity");
importCommands("lib.ui");
importCommands("lib.shizuku");
importCommands("lib.actions.ui");
importCommands("lib.every");
importCommands("lib.helper");

import bsh.This;
import java.io.File;
import android.content.BroadcastReceiver;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;
import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.Iterator;
import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor.DelayedWorkQueue;
import android.gesture.Gesture;
import bsh.NameSpace;
import com.joaomgcd.taskerm.action.java.JavaCodeSceneV2Helper;


everyGesture() {

	ToggleAction() {
		final String TURN_OFF = "off";
		final String TURN_ON = "on";
		final String TOGGLE = "toggle";
		return this;
	}

	final This ToggleAction = ToggleAction();

	EventType() {
		final int COMMAND = 0;
		final int EVENT_BRIDGE = 1;
		final int RELEASE = 0;
		final int LONG_PRESS = 1;
		return this;
	}

	final This EventType = EventType();

	int EventMethod = EventType.COMMAND;
	long EventDelay = 100;
	
	Map handles = new HashMap();
	boolean isReceiverRegistered = false;
	BroadcastReceiver orientationReceiver = createOrientationReceiver(handles);
	String LOG_FILE = LOG_FILE;
	String MAIN_DIRECTORY = MAIN_DIRECTORY;
	This Actions;
	This inspector;
	This thisManager;
	This packageManager = PackageManager();
	This updateManager = UpdateManager();
	updateManager.directoryPath = MAIN_DIRECTORY;
	This userService = UserServiceManager();
	This helperModeManager = HelperModeManager();
	This uiReader = UiReader();
	boolean updatePreRelease;
	This rule;
	int themeId;
	boolean useDarkTheme;
	boolean inHelperMode;
	Gesture lastGesture;
	This latestAction;
	int maxDisplayTextLength = 50;
	Map screenInfoEvents = new HashMap();

	ThreadFactory customThreadFactory = new ThreadFactory() {
		private AtomicInteger count = new AtomicInteger(0);
		public Thread newThread(Runnable r) {
			Thread thread = new Thread(r, "everyGesture_" + count.incrementAndGet());
			thread.setPriority(Thread.MAX_PRIORITY);
			return thread;
		}
	};

	ThreadPoolExecutor executor = new ThreadPoolExecutor(
		1, // Core size
		3, // Max size
		30, // Idle timeout
		TimeUnit.SECONDS, // Timeout unit
		new ArrayBlockingQueue(1), // Task wait-list
		customThreadFactory, // Thread factory
		new ThreadPoolExecutor.DiscardOldestPolicy()
	);

	/* ScheduledThreadPoolExecutor manages core size via constructor */
	ScheduledThreadPoolExecutor scheduledExecutor = new ScheduledThreadPoolExecutor(2, customThreadFactory);
	scheduledExecutor.setMaximumPoolSize(4);
	scheduledExecutor.setKeepAliveTime(30, TimeUnit.SECONDS);
	scheduledExecutor.allowCoreThreadTimeOut(true);
	scheduledExecutor.setRemoveOnCancelPolicy(true);
	scheduledExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());

	set() {
		String script = "addClassPath(\"" + MAIN_DIRECTORY + "\");";
		script +="\nimportCommands(\"lib\");";
		File libDir = new File(MAIN_DIRECTORY + "/lib");
		
		// Import every directory in the lib directory
		if (libDir.exists() && libDir.isDirectory()) {
			File[] files = libDir.listFiles();
			for (File file: files) {
				if (file.isDirectory()) {
					script += "\nimportCommands(\"lib." + file.getName() + "\");";
				}
			}
		}
		this.interpreter.eval(script);
	}

	add(This handle) {
		handles.put(handle.config.handleName, handle);
	}

	get(String handleName) {
		return handles.get(handleName);
	}

	getAll() {
		List list = new ArrayList(handles.values());
		list.sort(new Comparator() {
			compare(Object o1, Object o2) {
				String name1 = ((This) o1).config.handleName;
				String name2 = ((This) o2).config.handleName;
				return name1.compareTo(name2);
			}
		});
		return list;
	}

	remove(String handleName) {
		This handle = handles.get(handleName);
		handle.remove();
		handles.remove(handleName);
	}

	getHandleNames() {
		List list = new ArrayList();
		Set names = handles.keySet();
		for (String name: names) {
			list.add(name);
		}
		return list;
	}

	removeAll() {
		List keys = new ArrayList(handles.keySet());
		for (String key: keys) {
			try {
				remove(key);
			} catch (e) {
				log(e.getMessage(), "ERROR");
			}
		}
		handles.clear();
		unregister();
	}

	hasShowingHandle() {
		if (handles.isEmpty()) return false;
		for (This handle: handles.values()) {
			if (handle.isShown()) return true;
		}
		return false;
	}

	/* Register the receiver using the application context */
	register() {
		boolean hasShowingHandle = hasShowingHandle();
		if (!hasShowingHandle) {
			unregister();
		} else if (!isReceiverRegistered && orientationReceiver != null) {
			context.getApplicationContext().registerReceiver(orientationReceiver, new IntentFilter(Intent.ACTION_CONFIGURATION_CHANGED));
			isReceiverRegistered = true;
		}
	}

	unregister() {
		try {
			context.getApplicationContext().unregisterReceiver(orientationReceiver);
			isReceiverRegistered = false;
		} catch (e) {
			log("Failed to unregister orientation receiver. " + e.getMessage(), "ERROR");
		}
	}

	addByName(String handleName, boolean create) {
		This config = config = Config(handleName);
		config.load();
		This handle = Handle(config);
		if (config.handle.autoEnable && create) handle.show();
		add(handle);
		return handle;
	}

	addByName(String handleName) {
		addByName(handleName, false);
	}

	showHandle(String handleName) {
		This handle = handles.get(handleName);
		if (handle != null) {
			handle.show();
		}
	}

	removeHandle(String handleName) {
		This handle = handles.get(handleName);
		if (handle != null) {
			handle.remove();
		}
	}

	auto(boolean create) {
		addClassPath(MAIN_DIRECTORY);
		importCommands("lib");
		importCommands("lib.file");
		importCommands("lib.misc");
		importCommands("lib.activity");
		File parentDir = new File(MAIN_DIRECTORY + "/handles");
		if (!parentDir.exists() || !parentDir.isDirectory()) {
			throw new JavaCodeException("Path does not exist or is not a directory: " + dirPath);
		}
		log("Auto loading handles from " + parentDir.getAbsolutePath());
		List auto = new ArrayList();
		for (File handleDir: parentDir.listFiles()) {
			if (handleDir.isDirectory()) {
				String handleName = handleDir.getName();
				if (handleName.startsWith(".")) continue;
				String handleDirectory = handleDir.getAbsolutePath();
				File configFile = new File(handleDir, "config.json");
				File templateFile = new File(handleDir, "template.lib");
				// 				if (!configFile.exists() || !templateFile.exists()) continue;
				log("Auto loading handle: " + handleName + " at" + handleDirectory);
				auto.add(handleDirectory);
				addByName(handleName, create);
			}

		}
		return auto;
	}

	isAnyHandleAdded() {
		for (This handle: handles.values()) {
			if (handle.isAdded()) return true;
		}
		return false;
	}

	setInfoEvent(String name, This infoEvent) {
		screenInfoEvents.put(name, infoEvent);
	}

	deleteInfoEvent(String name) {
		screenInfoEvents.remove(name);
	}

	openMainActivity() {
		set();
		Main();
	}

	execute(Runnable task) {
		return executor.execute(task);
	}

	schedule(Runnable task, long delay) {
		return scheduledExecutor.schedule(task, delay, TimeUnit.MILLISECONDS);
	}

	schedule(Runnable task, long delay, long period) {
		return scheduledExecutor.scheduleAtFixedRate(task, delay, period, TimeUnit.MILLISECONDS);
	}

	cancelAllSchedules() {
		DelayedWorkQueue queue = scheduledExecutor.getQueue();
		Iterator iterator = queue.iterator();
		while (iterator.hasNext()) {
			RunnableScheduledFuture futureTask = (RunnableScheduledFuture) iterator.next();
			futureTask.cancel(true);
			iterator.remove();
		}
	}

	update() {
		if (updateManager != null) {
			if (updatePreRelease) {
				updateManager.updatePreRelease();
			}
			else {
				updateManager.update();
			}
		}

		reload();

	}

	updatePreRelease() {
		if (updateManager != null) {
			updateManager.updatePreRelease();
			reload();
		}
	}

	reload() {
		source(MAIN_DIRECTORY + "/gesture.java");
	}

	cleanResources() {
		removeAll();
		unregister();
		scheduledExecutor.shutdownNow();
		executor.shutdownNow();
	}

	inspectVariables(This targetThis) {
		set();
		InspectVariablesDialog(targetThis);
	}

	inspectVariables(NameSpace targetNameSpace) {
		set();
		InspectVariablesDialog(targetNameSpace);
	}

	inspectVariables(JavaCodeSceneV2Helper targetTasker) {
		set();
		InspectVariablesDialog(targetTasker);
	}

	return this;
}

File handle = new File(MAIN_DIRECTORY + "/handles");
if (!handle.exists() || !handle.isDirectory()) handle.mkdirs();
File script = new File(MAIN_DIRECTORY + "/scripts");
if (!script.exists() || !script.isDirectory()) script.mkdirs();

String varName = "everyGesture";

if (tasker.getJavaVariable(varName) != null) {
	This old = tasker.getJavaVariable(varName);
	try {
		boolean hasClean = old.namespace.getMethod("cleanResources", new Class[] {});
		if (!hasClean) throw new Exception("err");
		old.cleanResources();
		tasker.setJavaVariable(varName, null);
	} catch (e) {
		old.removeAll();
		old.unregister();
		old.scheduledExecutor.shutdownNow();
		old.executor.shutdownNow();
		tasker.setJavaVariable(varName, null);
	}
}

This every = everyGesture();
tasker.setJavaVariable(varName, every);
every.auto(true);
setVar(String name, Object value) {
	every.namespace.setVariable(name, value, false);
}

This Actions = Actions();
Runnable refreshList = new Runnable() {
	public void run() {
		Actions.refresh();
	}
};
every.execute(refreshList);
setVar("Actions", Actions);

This inspector = MethodInspector(this);
inspector.read();
setVar("inspector", inspector);

This manager = ThisManager(this);
setVar("thisManager", manager);
manager.make("lib/actions/.*\\.bsh$", 2, false);
every.THIS = this;

String command = "everyGesture=:=start";
tasker.sendCommand(command);

String sampleScript ="""
InspectVariablesDialog(this);""";

writeFile(sampleScript, MAIN_DIRECTORY + "/scripts/Inspect Variable List in Evaluate actions.java");