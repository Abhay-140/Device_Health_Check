package com.example.devicehealthcheck;

import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.Manifest;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;

public class Microphone {

    public static boolean isPrimaryMicrophoneAvailable(Context context) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions((testin_activity) context, new String[]{Manifest.permission.RECORD_AUDIO}, 0);
            return false;
        }

        AudioRecord audioRecord = null;
        boolean isAvailable = true;

        try {
            int bufferSize = AudioRecord.getMinBufferSize(44100, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);
            audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, 44100, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT, bufferSize);

            if (audioRecord.getState() != AudioRecord.STATE_INITIALIZED) {
                isAvailable = false;
            }
        } catch (Exception e) {
            isAvailable = false;
        } finally {
            if (audioRecord != null) {
                audioRecord.release();
            }
        }

        return isAvailable;
    }

    public static boolean isSecondaryMicrophoneAvailable(Context context) {
        MediaRecorder recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        recorder.setOutputFile(new File(context.getCacheDir(), "MediaUtil#micAvailTestFile").getAbsolutePath());
        boolean available = true;
        try {
            recorder.prepare();
            recorder.start();

        }
        catch (Exception exception) {
            available = false;
        }
        recorder.release();
        return available;
    }

}

