# How to create a handle

1. Launch Main Activity task
2. Add button (FAB)
3. Add handle.

<video height ="480" src="https://github.com/user-attachments/assets/1e5f09c2-352f-4915-9472-2240091a4f16" controls="controls" muted="muted" playsinline="playsinline"></video>

&nbsp;

# How to add a new gesture and manage gesture templates

1. (Pen icon) Gesture.
2. Click FAB button > Add Gesture.

<video height ="480" src="https://github.com/user-attachments/assets/464366a3-511b-49ae-9c3e-7cff84a33947" controls="controls" muted="muted" playsinline="playsinline"></video>

&nbsp;

# How to add a new action.

We can add action by.

1. Click FAB button > click Add Action.
2. Click Handle card / Edit rules > click a gesture card > click Add (+) button.

<video height ="480" src="https://github.com/user-attachments/assets/028f678f-0d1b-4375-92a4-833a09dc3664" controls="controls" muted="muted" playsinline="playsinline"></video>

Or [we can intercept the default tasker command directly here.](/readme/React%20to%20default%20command.md)

&nbsp;

# How to delete a handle

1. Delete the handle folder inside `chosen folder/handles` 
2. Add "." as a prefix and it will not get listed.


&nbsp;

# How to delete a gesture

1. Click Gestures on a handle card > Click Delete chip > Select > Ok.
2. CLick Handle card / Edit rules > Click a gesture card > click triple dots > delete gesture.

<video height ="480" src="https://github.com/user-attachments/assets/56767f1b-e1e5-478f-8b23-cf4a4626764c" controls="controls" muted="muted" playsinline="playsinline"></video>

&nbsp;

# How to delete an action?

1. Click Handle card / Edit rules
2. Click a gesture card. 
3. Click delete button (trash bin).

<video height ="480" src="https://github.com/user-attachments/assets/2218257a-7ed8-4bde-aa95-61cdc9ef9181" controls="controls" muted="muted" playsinline="playsinline"></video>

&nbsp;

# Changing theme

Go to When EveryGesture start profile and add this java code `everyGesture.useDarkTheme = false`.

&nbsp;

# Recognizing straight Lines with or without Dollar Q

During my test, the gesture library alone isn't sufficient recognizing straight lines and their direction. This is the comparison demo.

<video height ="480" src="https://github.com/user-attachments/assets/aaa81bd6-390f-4a8a-9756-7cb2a935a899" controls="controls" muted="muted" playsinline="playsinline"></video>

