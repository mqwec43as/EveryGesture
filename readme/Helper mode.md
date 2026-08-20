


This project has a helper mode. That will help us configure Tasker profiles and intercept default command and screen events.


&nbsp;

# What does Helper Mode do?
Once we enter the default mode. The following will occurs.

1. Postpone any performed gesture.
2. Display minimal information about the gesture.
3. If a gesture is performed on Command Event edit screen, it will automatically fill the necessary formats for command and variables.

&nbsp;

# Demo
<video height ="480" src=" " controls="controls" muted="muted" playsinline="playsinline"></video>

&nbsp;

# Auto fill
Auto fill is done by using Accessibility Service.

&nbsp;

# How to use

There are two ways to toggle the mode.

1. **Assign an action to a gesture.** The action is available in Every action section. *Recommended*.
2. **Automatically set the mode via `everyGesture.inHelperMode`** with boolean value `true` or `false`.

```java
// Enter helper mode
everyGesture.inHelperMode = true;
```





