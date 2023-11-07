package com.example.devicehealthcheck;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class testin_activity extends AppCompatActivity {
    AlertDialog.Builder builder;

    private Handler handler;
    ListView listView;
    private InternetManager internetManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testin);
        builder = new AlertDialog.Builder(this);
        internetManager=new InternetManager(this,null);
        listView = findViewById(R.id.Listview);


        List<CardItem> cardItemList = new ArrayList<>();
        cardItemList.add(new CardItem(R.drawable.img2, "Rear Camera", "Test the rear camera by taking a picture of your hand."));
        cardItemList.add(new CardItem(R.drawable.img3, "Front Camera", "Test the forward facing camera by taking a picture of your face."));
        cardItemList.add(new CardItem(R.drawable.micpri, "Microphone (Primary)", "Were you able to hear an automated message?"));
        cardItemList.add(new CardItem(R.drawable.micsec, "Microphone (Secondary)", "Were you able to hear an automated message?"));
        cardItemList.add(new CardItem(R.drawable.img1, "Bluetooth", "Check your phone has bluetooth service or not."));
        cardItemList.add(new CardItem(R.drawable.root, "Root Status", "Is your phone rooted or not?"));
        cardItemList.add(new CardItem(R.drawable.img4, "Call Check", "Were you able to hear an automated call?"));
        cardItemList.add(new CardItem(R.drawable.img5, "Accelerometer", "Did you hear the music?"));
        cardItemList.add(new CardItem(R.drawable.gps, "GPS", "How many ?"));
        cardItemList.add(new CardItem(R.drawable.img7, "Gyroscope", "Were you able to hear an automated message?"));




        CardAdapter adapter = new CardAdapter(cardItemList,this);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener((adapterView, view, position, id) -> {
            CardItem itemAtPos = (CardItem)adapterView.getItemAtPosition(position);
            Button firstButton = view.findViewById(R.id.button3);


        });



        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                internetManager.updateNetworkStatus();
                handler.postDelayed(this, 1000);
            }
        }, 1000);
    }




}

