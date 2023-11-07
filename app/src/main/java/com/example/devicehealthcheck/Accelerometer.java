package com.example.devicehealthcheck;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;

public class Accelerometer {

    public static boolean isAccelerometerAvailable(Context context) {
        SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);

        if (sensorManager != null) {
            Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

            return accelerometerSensor != null;
        }

        return false;
    }
}

