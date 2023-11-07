package com.example.devicehealthcheck;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;

public class Camerachecker {

    public static boolean isRearCameraAvailable(Context context) {
        CameraManager cameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);

        try {
            for (String cameraId : cameraManager.getCameraIdList()) {
                CameraCharacteristics characteristics = cameraManager.getCameraCharacteristics(cameraId);
                Integer lensFacing = characteristics.get(CameraCharacteristics.LENS_FACING);

                if (lensFacing != null && lensFacing == CameraCharacteristics.LENS_FACING_BACK) {
                    return true; // Rear camera is available
                }
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

        return false; // Rear camera is not available
    }
    public static boolean isFrontCameraAvailable(Context context) {
        CameraManager cameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);

        try {
            for (String cameraId : cameraManager.getCameraIdList()) {
                CameraCharacteristics characteristics = cameraManager.getCameraCharacteristics(cameraId);
                Integer lensFacing = characteristics.get(CameraCharacteristics.LENS_FACING);

                if (lensFacing != null && lensFacing == CameraCharacteristics.LENS_FACING_FRONT) {
                    return true; // Front camera is available
                }
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

        return false; // Front camera is not available
    }
}

