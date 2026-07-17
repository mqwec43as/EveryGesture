> [!NOTE] 
*This documentation was generated using AI with very minimal human editing.*

# everyGesture

everyGesture is a global Java variable initialized by [`gesture.java`](/code/gesture.java). Once it is created, it can be used across any Java Code action in Tasker.

It exposes helper variables and methods for managing handles, loading runtime helpers, controlling background execution, and coordinating updates.

## 1. Usage

```java
// This everyGesture = tasker.getJavaVariable("everyGesture");

// Show a handle
everyGesture.showHandle("RightMiddle");

// Remove a handle 
everyGesture.removeHandle("RightMiddle");
```

&nbsp;

## 2. Variables

### 2.1 Environment

| Variable            | Type                 | Description                                                                                           | Default Value     |
| :------------------ | :------------------- | :---------------------------------------------------------------------------------------------------- | :---------------- |
| `MAIN_DIRECTORY`    | `String`             | Main project directory derived from the script source path.                                           | `null`            |
| `LOG_FILE`          | `String`             | Log file path inside the main directory.                                                             | `null`            |
| `handles`           | `Map`                | Stores all loaded [Handle](/code/lib/Handle.bsh) objects by name.                                                            | `new HashMap()`   |
| `isReceiverRegistered` | `boolean`          | Tracks whether the orientation receiver is currently registered.                                     | `false`           |
| `orientationReceiver` | `BroadcastReceiver` | Receiver used for configuration change events.                                                       | `null`            |
| `Actions`           | `This`               | Shared action manager instance.                                                                      | `null`            |
| `inspector`         | `This`               | Method inspector instance created by [`MethodInspector`](/code/lib/misc/MethodInspector.bsh).       | `null`            |
| `latestAction`      | `This`               | Latest Action recognized.                                                                              | `null`            |
| `latestGesture`     | `Gesture`            | Latest Gesture recognized.                                                                           | `null`            |
| `thisManager`       | `This`               | Manager used to load helper scripts into the runtime.                                                | `null`            |
| `packageManager`    | `This`               | Package manager instance.                                                                            | `null`            |
| `updateManager`     | `This`               | Update manager instance created by [`UpdateManager`](/code/lib/UpdateManager.bsh).                 | `null`            |
| `updatePreRelease`  | `boolean`            | Controls whether pre-release updates are checked.                                                   | `false`           |
| `rule`              | `This`               | Placeholder last Rule object.                                                                     | `null`            |
| `themeId`           | `int`                | Current theme identifier.                                                                            | `0`               |
| `useDarkTheme`      | `boolean`            | Indicates whether dark theme should be used.                                                         | `false`           |
| `inHelperMode`      | `boolean`            | Tracks whether the runtime is running in helper mode.                                                | `false`           |
| `maxDisplayTextLength` | `int`               | Maximum display text length for UI surfaces.                                                         | `50`              |
| `screenInfoEvents`  | `Map`                | Stores screen information event objects by name.                                                     | `new HashMap()`   |
| `executor`          | `ThreadPoolExecutor` | Background executor used for asynchronous tasks.                                                    | custom pool      |
| `scheduledExecutor` | `ScheduledThreadPoolExecutor` | Scheduler used for delayed and repeating tasks.                                              | custom pool      |

### 2.2 Runtime & Behavior

| Variable            | Type       | Description                                                                                 | Default Value |
| :------------------ | :--------- | :------------------------------------------------------------------------------------------ | :------------ |
| `customThreadFactory` | `ThreadFactory` | Thread factory used to name executor threads and set high priority.                     | custom factory |
| `updatePreRelease`  | `boolean`  | Use pre-release updates during `update()`.                                                   | `false`       |
| `isReceiverRegistered` | `boolean` | Whether the orientation receiver is currently registered.                                   | `false`       |
| `maxDisplayTextLength` | `int`     | Limit used for display text in UI components.                                                | `50`          |

## 3. Methods / Functions

### Handle management

1. [`add(This handle)`](/code/gesture.java)

   Add a [Handle](/code/lib/Handle.bsh) object to the internal `handles` map by passing [Handle](/code/lib/Handle.bsh).

2. [`get(String handleName)`](/code/gesture.java)

   Return a stored [Handle](/code/lib/Handle.bsh) by name.

3. [`getAll()`](/code/gesture.java)

   Return a sorted list of all handles.

4. [`remove(String handleName)`](/code/gesture.java)

   Remove and dispose of a [Handle](/code/lib/Handle.bsh) by name.

5. [`getHandleNames()`](/code/gesture.java)

   Return a list of all currently loaded [Handle](/code/lib/Handle.bsh) names.

6. [`removeAll()`](/code/gesture.java)

   Remove every showing [Handle](/code/lib/Handle.bsh), unregister the receiver, and clear the runtime state.

7. [`hasShowingHandle()`](/code/gesture.java)

   Return whether any currently loaded [Handle](/code/lib/Handle.bsh) is visible.

8. [`register()`](/code/gesture.java)

   Register the orientation receiver when at least one [Handle](/code/lib/Handle.bsh) is showing.

9. [`unregister()`](/code/gesture.java)

   Unregister the orientation receiver if it is currently registered.

10. [`showHandle(String handleName)`](/code/gesture.java)

    Show a [Handle](/code/lib/Handle.bsh) on the screen by its name.

11. [`removeHandle(String handleName)`](/code/gesture.java)

    Remove a [Handle](/code/lib/Handle.bsh) from the screen by its name.

12. [`addByName(String handleName, boolean create)`](/code/gesture.java)

    Load a [Handle](/code/lib/Handle.bsh) configuration by name and optionally show it immediately.

13. [`addByName(String handleName)`](/code/gesture.java)

    Convenience overload for `addByName(handleName, false)`.

14. [`auto(boolean create)`](/code/gesture.java)

    Scan the handles directory, auto-load any [Handle](/code/lib/Handle.bsh) folders, and optionally show them.

15. [`isAnyHandleAdded()`](/code/gesture.java)

    Return whether any [Handle](/code/lib/Handle.bsh) has been added to the screen.

### State and event helpers

1. [`setInfoEvent(String name, This infoEvent)`](/code/gesture.java)

   Register a screen-info event object under a specific name.

2. [`deleteInfoEvent(String name)`](/code/gesture.java)

   Remove a screen-info event object by name.

### Execution helpers

1. [`execute(Runnable task)`](/code/gesture.java)

   Run a task on the internal executor.

2. [`schedule(Runnable task, long delay)`](/code/gesture.java)

   Schedule a delayed task using the internal scheduler.

3. [`schedule(Runnable task, long delay, long period)`](/code/gesture.java)

   Schedule a repeating task using the internal scheduler.

4. [`cancelAllSchedules()`](/code/gesture.java)

   Cancel all pending scheduled tasks.

### Update and startup helpers

1. [`openMainActivity()`](/code/gesture.java)

   Launch the main UI activity.

2. [`update()`](/code/gesture.java)

   Run the update manager, then reload the runtime.

3. [`updatePreRelease()`](/code/gesture.java)

   Run the pre-release update flow and reload the runtime.

&nbsp;

# 4. Dependency & Dependant

everyGesture depends on the command libraries imported at startup from [`code/lib`](/code/lib) and uses shared helpers such as [`Handle`](/code/lib/Handle.bsh), [`Config`](/code/lib/Config.bsh), [`UpdateManager`](/code/lib/UpdateManager.bsh), [`MethodInspector`](/code/lib/misc/MethodInspector.bsh), and [`PackageManager`](/code/lib/misc/PackageManager.bsh).

The runtime also initializes:
- [`Actions`](/code/lib/Actions.bsh)
- [`ThisManager`](/code/lib/ThisManager.bsh)
- the orientation receiver created by `createOrientationReceiver(every.handles)`
- the [Handle](/code/lib/Handle.bsh) folders inside the main handles directory

Other helpers in [`code/lib`](/code/lib) and [`code/lib/ui`](/code/lib/ui) are loaded through the shared runtime and can access the same global `everyGesture` instance.
    