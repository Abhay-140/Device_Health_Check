package com.example.devicehealthcheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn=findViewById(R.id.button);

        btn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),testin_activity.class);
            startActivity(intent);
        });
    }
}