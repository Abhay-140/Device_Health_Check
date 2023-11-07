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

        CardItem cardItem = data.get(position);

        titleView.setText(cardItem.getTitle());
        img.setImageResource(cardItem.getImageResource());
        des.setText(cardItem.getDescription());

        return rowView;
    }



}



