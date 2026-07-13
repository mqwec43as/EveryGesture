
&nbsp;

### Features
1. Do action based on performed gesture.
2. JSON configuration file.
3. UI to draw gesture and configure action.

&nbsp;

### Demo 
What's in the video.
1. Manipulate volume at different speed.
2. Scroll down until lifted up.

Video is also available in the comment as well. Can't embed in the post since r/tasker doesn't support that.


&nbsp;

# Notes
The UI for the properties configuration is not WYSIWYG . We have to save first to see the change.

If there are stuff that doesn't fit your likings, e.g UI/UX, trigger behaviour, etc, feel free to edit the project however you like!

For bug/crash, you could report it here. https://github.com/mqwec43as/EveryGesture/issues

&nbsp;

## ! Memory Leak !
I notice sometimes I have a higher ram usage up to 100mb (from 350mb idle to 450mb) even after the activites are not foreground. Usually I always kill Tasker after done configuring the handle.

It's probably because the activities are not finished properly since I only monitor the decor view. Or other stuff that I have no idea about (I'm not a dev.).

## How to create a handle
1. Launch Main Activity task > Add button (FAB) > Add handle.

## How to add a gesture and manage gesture templates
1. (Pen icon) Gesture.
2. FAB > Gesture.

## How to add actions.
We can add action by.
   1. FAB button.
   2. Long pressing the card for the handle.
   3. Expand the handle card by clicking and click the action for registered gesture.

## How to delete a handle?
Delete the handle folder inside `chosen folder/handles` or add "." as a prefix and it will not get listed.

## How to delete an action?
1. Click handle card > click gesture card outside the actions > trash bin icon.

## Changing theme
Go to When EveryGesture start profile and add this java code `everyGesture.useDarkTheme = false`.








