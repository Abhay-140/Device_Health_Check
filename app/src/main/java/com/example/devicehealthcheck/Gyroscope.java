package com.example.devicehealthcheck;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;

public class Gyroscope {
    public static boolean isGyroscopeAvailable(Context context) {
        SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        Sensor gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        return gyroscopeSensor != null;
    }
}

