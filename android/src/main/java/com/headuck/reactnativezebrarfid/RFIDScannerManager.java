package com.ivaldovinos.reactnativezebrarfid;

import android.util.Log;

import com.facebook.react.modules.core.DeviceEventManagerModule;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.LifecycleEventListener;

public class RFIDScannerManager extends ReactContextBaseJavaModule implements LifecycleEventListener {

    public final ReactApplicationContext context;

    private RFIDScannerThread scannerthread = null;

    public RFIDScannerManager(ReactApplicationContext reactContext) {
        super(reactContext);

        this.context = reactContext;
        this.context.addLifecycleEventListener(this);

        this.scannerthread = new RFIDScannerThread(this.context) {

            @Override
            public void dispatchEvent(String name, WritableMap data) {
                RFIDScannerManager.this.context.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(name, data);
            }

            @Override
            public void dispatchEvent(String name, String data) {
                RFIDScannerManager.this.context.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(name, data);
            }

            @Override
            public void dispatchEvent(String name, WritableArray data) {
                RFIDScannerManager.this.context.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(name, data);
            }

        };
        scannerthread.start();

        Log.v("RFID", "RFIDScannerManager created");

    }


    @Override
    public String getName() {
        return "RFIDScannerManager";
    }

    @Override
    public void onHostResume() {
        if (this.scannerthread != null) {
            this.scannerthread.onHostResume();
        }
    }

    @Override
    public void onHostPause() {
        if (this.scannerthread != null) {
            this.scannerthread.onHostPause();
        }
    }

    @Override
    public void onHostDestroy() {
        if (this.scannerthread != null) {
            this.scannerthread.onHostDestroy();
        }
    }

    @Override
    public void onCatalystInstanceDestroy() {
        if (this.scannerthread != null) {
            this.scannerthread.onCatalystInstanceDestroy();
        }
    }

    @ReactMethod
    public void init() {
        if (this.scannerthread != null) {
            this.scannerthread.init(context);
        }
    }

    @ReactMethod
    public void reconnect() {
        if (this.scannerthread != null) {
            this.scannerthread.reconnect();
        }
    }

    @ReactMethod
    public void read(ReadableMap config) {
        if (this.scannerthread != null) {
            this.scannerthread.read(config);
        }
    }

    @ReactMethod
    public void cancel() {
        if (this.scannerthread != null) {
            this.scannerthread.cancel();
        }
    }

    @ReactMethod
    public void shutdown() {
        if (this.scannerthread != null) {
            this.scannerthread.shutdown();
        }
    }

}
