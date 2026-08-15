
<div align="center">
<h4><b>This project was developed with the assistance of generative AI tools and owner doesn't have android dev experience.</b></h4>


&nbsp;
# Every Gesture
Draw gesture project for android automation app called **[Tasker](https://play.google.com/store/apps/details?id=net.dinglisch.android.taskerm)** 

[![Tasker](https://img.shields.io/badge/Tasker-v6.7.6-ffdbcc?style=for-the-badge&labelColor=e65100)](https://play.google.com/store/apps/details?id=net.dinglisch.android.taskerm)
[![Downloads](https://img.shields.io/github/downloads/mqwec43as/EveryGesture/total?label=downloads&labelColor=27303D&color=0D1117&logo=github&logoColor=FFFFFF&&style=for-the-badge)](https://github.com/mqwec43as/EveryGesture/releases/latest/download/EveryGesture.zip)
[![Stable](https://img.shields.io/github/v/release/mqwec43as/EveryGesture?sort=date&display_name=tag&style=for-the-badge&label=stable
)](https://github.com/mqwec43as/EveryGesture/releases/latest)
[![Beta](https://img.shields.io/github/v/release/mqwec43as/EveryGesture?include_prereleases&sort=semver&display_name=tag&style=for-the-badge&label=beta
)](https://github.com/mqwec43as/EveryGesture/releases)

</div>
&nbsp;

<div align="center">

# FEATURES

</div>

&nbsp;

### 1. Draw and do something
Draw any shape and do certain actions.

<video height ="480" src="https://github.com/user-attachments/assets/942ef520-2f22-4a49-8c33-dbc2ede39a13" controls="controls" muted="muted" playsinline="playsinline"></video>

1. Open Reddit by drawing R.
2. Swipe to control volume.

&nbsp;

### 2. JSON configuration file
Config file is in JSON.
```json
"handle": {
    "useAccessibility": false,
    "autoEnable": false,
    "color": "#FFFFFFFF",
    "dashInterval": [],
    "cornerRadius": 200,
    "size": [
      30,
      500
    ], ...
  }
```

&nbsp;

### 3. UI
UI to register gesture and supported actions.

<video height ="480" src="https://github.com/user-attachments/assets/73af4c83-b6cb-483b-acab-743b4aa382c4" controls="controls" muted="muted" playsinline="playsinline"></video>

&nbsp;

### 4. Control via Java code
Control handles via Java code
```java
// show a handle
everyGesture.showHandle("Test");

// remove a handle from showing
everyGesture.removeHandle("Test");

// Get a handle object
handle = everyGesture.get("Test");

// Mute a handle
handle.mute();
handle.unmute();

// Make it not interactable
handle.setInteractable(false); 
```

&nbsp;

<div align="center">

# FAQ & DOCUMENTATION

</div>

1. [What is everyGesture?](/readme/What%20is%20everyGesture.md)
2. [How to intercept default command](/readme/React%20to%20default%20command.md)
3. [What is screen event and how to use it](/readme/What%20is%20Screen%20Event%20and%20how%20to%20react%20to%20it.md)
4. [Configure profile to intercept command or screen event in Helper Mode](/readme/Helper%20mode.md)
