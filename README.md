
# React native RFID reader 

## for Zebra RFD8500 and MC33 readers

React-native module for scanning RFID tags with Zebra RFD8500 and MC33

### Install:
Install the module
```bash
npm install --save @ivaldovinos/react-native-zebra-rfid
```

Link native dependencies

```bash
react-native link @ivaldovinos/react-native-zebra-rfid
```


Add the `node_modules` library directory in repositories under `build.gradle`, e.g. (added the `flatDir` line):


```groovy
allprojects {
	repositories {
        google()
        mavenLocal()
        jcenter()
        maven {
            // All of React Native (JS, Obj-C sources, Android binaries) is installed from npm
            url "$rootDir/../node_modules/react-native/android"
        }
        // Manually added for react-native-zebra-rfid
        flatDir { dirs "$rootDir/../node_modules/@ivaldovinos/react-native-zebra-rfid/android/libs" }
    }
}
```

This module includes the Zebra RFID `API3_SDK_2.0.1.27.aar` file included. Depending on your setup you may need to override some settings in the aar, e.g. `minsdk` or `allowBackup`, in the android manifest.

### Usage:

```javascript
import RFIDScanner, { RFIDScannerEvent } from '@ivaldovinos/react-native-zebra-rfid';

// Init and connect
RFIDScanner.init();

// Register callback
RFIDScanner.on(RFIDScannerEvent.TAGS, onRfidResult);

// Remove callback
RFIDScanner.removeon(RFIDScannerEvent.TAGS, onRfidResult);

// Shutdown
RFIDScanner.shutdown();

// Callback, tags is an array of strings
// Read triggered by hardware trigger, or 
// programmatically by calling
// RFIDScanner.read() or RFIDScanner.cancel()

const onRfidResult = (tags) => {
    console.info(' TAGS: ' + JSON.stringify(tags));
}
```

