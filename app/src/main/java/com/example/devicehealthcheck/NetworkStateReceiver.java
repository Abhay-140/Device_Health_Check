package com.example.devicehealthcheck;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.TextView;

public class NetworkStateReceiver extends BroadcastReceiver {


    public NetworkStateReceiver() {
       Log.d("constructor msg","constructor ran") ;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        updateNetworkStatus(context);
    }

    public static IntentFilter getNetworkStateIntentFilter() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        return filter;
    }

    private void updateNetworkStatus(Context context) {
        if (InternetManager.isNetworkAvailable(context)) {
            Log.d("msg","INTERNET CONNECTION IS : ON");
        } else {
            Log.d("msg","INTERNET CONNECTION IS : OFF");
        }
    }
}
