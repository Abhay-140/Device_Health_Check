package com.example.devicehealthcheck;

import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class InternetManager {

    private Context context;
    private AlertDialog alertDialog;

    InternetManager(Context c,AlertDialog al){
        this.alertDialog=al;
        this.context=c;
    }
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        return false;
    }

    void updateNetworkStatus() {
        if (!InternetManager.isNetworkAvailable(context)) {
            // Create and show the AlertDialog if not already shown
            if (alertDialog == null || !alertDialog.isShowing()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("No Internet Connection");
                builder.setMessage("Please check your internet connection and try again");
                builder.setPositiveButton("Check", (dialog, which) -> alertDialog.dismiss());
                alertDialog = builder.create();
                alertDialog.show();
            }
        } else {
            if (alertDialog != null && alertDialog.isShowing()) {
                alertDialog.dismiss();
            }
        }
    }

}


