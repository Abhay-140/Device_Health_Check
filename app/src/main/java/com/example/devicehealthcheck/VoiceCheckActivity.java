package com.example.devicehealthcheck;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.content.Intent;
import android.net.Uri;

public class VoiceCheckActivity extends AppCompatActivity {

    private static final int REQUEST_CALL_PERMISSION = 1;
    private boolean isCallOngoing = false;
    private AlertDialog voiceCheckDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_check);

        Button makeCallButton = findViewById(R.id.makeCallButton);
        makeCallButton.setOnClickListener(v -> checkAndMakeCall());

        // Initialize the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Voice Check");
        builder.setMessage("Was the voice clear during the call?");
        builder.setPositiveButton("Yes", (dialog, which) -> {
            // Handle the "Yes" response
            Toast.makeText(this, "Voice was clear", Toast.LENGTH_SHORT).show();
        });
        builder.setNegativeButton("No", (dialog, which) -> {
            // Handle the "No" response
            Toast.makeText(this, "Voice was not clear", Toast.LENGTH_SHORT).show();
        });
        voiceCheckDialog = builder.create();
    }

    private void checkAndMakeCall() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PERMISSION);
        } else {
            makeCall();
        }
    }

    private void makeCall() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:121"));
        startActivity(callIntent);

        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        PhoneStateListener phoneStateListener = new PhoneStateListener() {
            @Override
            public void onCallStateChanged(int state, String phoneNumber) {
                super.onCallStateChanged(state, phoneNumber);
                if (state == TelephonyManager.CALL_STATE_IDLE) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(VoiceCheckActivity.this);
                    builder.setTitle("Voice Check");
                    builder.setMessage("Was the voice clear during the call?");
                    builder.setPositiveButton("Yes", (dialog, which) -> {
                        // Handle the "Yes" response
                        Toast.makeText(VoiceCheckActivity.this, "Voice was clear", Toast.LENGTH_SHORT).show();
                    });
                    builder.setNegativeButton("No", (dialog, which) -> {
                        // Handle the "No" response
                        Toast.makeText(VoiceCheckActivity.this, "Voice was not clear", Toast.LENGTH_SHORT).show();
                    });
                    builder.create().show();
                }
            }
        };
        telephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makeCall();
            } else {
                Toast.makeText(this, "Permission denied to make a call.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
