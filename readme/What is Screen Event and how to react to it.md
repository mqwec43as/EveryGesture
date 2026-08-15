# What is Screen Event?

In a nutshell it's a screen reader and we can react to it based on the screen context itself and how we trigger the event.

&nbsp;

# How It Works

Behind the scene the event is handled by **[`screenInfo.bsh`](https://www.google.com/search?q=/code/lib/actions/screenInfo.bsh)**. This method utilites:

1. **[`UiReader`](https://www.google.com/search?q=/code/lib/actions/ui/UiReader.bsh)** to read the screen.
2. **[`getUsageEvents`](https://www.google.com/search?q=/code/lib/misc/getUsageEvents.bsh)** to get the foreground activity.

Then upon being triggered, it will do the following in order.

1. Set the information as Tasker global variables.
2. Fire default command with the information.

&nbsp;

# Global Variables

| Variable | Description | Example |
| --- | --- | --- |
| `%EG_AppActivity` | Foreground activity | `com.google.android.apps.photos.home.HomeActivity` |
| `%EG_AppName` | App label | `Photos` |
| `%EG_AppPackage` | App package name | `app.google.android.apps.photos` |
| `%EG_Text` | Text available on screen separated by two lines | Look below  |
| `%EG_TextResource` | Similar to `%EG_Text`, but paired with the ID/class | Look below. |


&nbsp;

# Text & Text Resource

Text & Text Resource is basically the android tree obtained by accessibility service. However the format is simplified.

```
[view resource id / class name #index]
Text related to the id
```
`%EG_Text`  *will not have the class / resource id*.


&nbsp;


**Example**
```
[android.view.View$3]
Share

[app.google.android.apps.photos:id/photos_overflow_icon]
More options

```


&nbsp;


# How to react
The same as default command, but we can now utilize the global variables as a state.

```
if %EG_AppName ~ tasker/youtube

AND 

if %EG_Text ~ *Tasker*

AND

if %EG_TextResource ~R *add\]\nAdd*
```d