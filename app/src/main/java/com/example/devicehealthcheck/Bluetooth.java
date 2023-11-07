package com.example.devicehealthcheck;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;

public class Bluetooth {
    public static boolean isBluetoothAvailable(Context context) {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        return bluetoothAdapter != null;
    }

    public static boolean isBluetoothEnabled(Context context) {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        return bluetoothAdapter != null && bluetoothAdapter.isEnabled();
    }
}

