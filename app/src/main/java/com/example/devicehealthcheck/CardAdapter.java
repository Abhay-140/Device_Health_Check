package com.example.devicehealthcheck;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CardAdapter extends BaseAdapter {

    private List<CardItem> data;
    private Context context;

    public CardAdapter(List<CardItem> cardItems,Context context) {
        this.data = cardItems;
        this.context=context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.test_list, parent, false);

        TextView titleView = rowView.findViewById(R.id.textView4);
        ImageView img = rowView.findViewById(R.id.imageView3);
        TextView des = rowView.findViewById(R.id.textView3);
        Button firstButton = rowView.findViewById(R.id.button3);

        CardItem itemAtPos = data.get(position);

        titleView.setText(itemAtPos.getTitle());
        img.setImageResource(itemAtPos.getImageResource());
        des.setText(itemAtPos.getDescription());

        firstButton.setOnClickListener(v -> {
            if (itemAtPos.getTitle().equals("Front Camera")) {
                if (Camerachecker.isFrontCameraAvailable(v.getContext())) {
                    firstButton.setText("Success");
                } else {
                    firstButton.setText("Failed");

                }
            }
            else if (itemAtPos.getTitle().equals("Rear Camera")) {
                if (Camerachecker.isRearCameraAvailable(v.getContext())) {
                    firstButton.setText("Success");
                } else {
                    firstButton.setText("Failed");
                }
            }
            else if (itemAtPos.getTitle().equals("Bluetooth")) {
                boolean isBluetoothAvailable = Bluetooth.isBluetoothAvailable(v.getContext());
                if (isBluetoothAvailable) {
                    firstButton.setText("Success");
                } else {
                    firstButton.setText("Failed");
                }
            }
            else if (itemAtPos.getTitle().equals("Microphone (Primary)")) {
                boolean priMic = Microphone.isPrimaryMicrophoneAvailable(v.getContext());
                if (priMic) {
                    firstButton.setText("Success");
                } else {
                    firstButton.setText("Failed");
                }
            }
            else if (itemAtPos.getTitle().equals("Microphone (Secondary)")) {
                boolean secMic = Microphone.isSecondaryMicrophoneAvailable(v.getContext());
                if (secMic) {
                    firstButton.setText("Success");
                } else {
                    firstButton.setText("Failed");
                }
            }
            else if (itemAtPos.getTitle().equals("Root Status")) {
                RootChecker rootChecker = new RootChecker();
                if (rootChecker.isRooted()) {
                    firstButton.setText("Success");
                } else {
                    firstButton.setText("Failed");
                }
            }
            else if (itemAtPos.getTitle().equals("Call Check")) {
                Intent intent = new Intent(v.getContext(), VoiceCheckActivity.class);
                v.getContext().startActivity(intent);
            }
            else if (itemAtPos.getTitle().equals("Accelerometer")) {
                if (Accelerometer.isAccelerometerAvailable(v.getContext())) {
                    firstButton.setText("Success");
                } else {
                    firstButton.setText("Failed");
                }
            }
            else if (itemAtPos.getTitle().equals("GPS")) {
                boolean isGPS = GPS.isGPSEnabled(v.getContext());
                if (isGPS) {
                    firstButton.setText("Success");
                } else {
                    firstButton.setText("Failed");
                }
            }
            else {
                boolean isGyroscopeActive = Gyroscope.isGyroscopeAvailable(v.getContext());
                if (isGyroscopeActive) {
                    firstButton.setText("Success");
                } else {
                    firstButton.setText("Failed");
                }
            }
        });

        return rowView;
    }



}



