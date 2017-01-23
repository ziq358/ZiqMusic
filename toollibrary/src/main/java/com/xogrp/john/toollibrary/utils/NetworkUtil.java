package com.xogrp.john.toollibrary.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by john on 23/01/2017.
 */

public class NetworkUtil {
    public static boolean isWifiAvailable(Context context) {
        try {
            ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                NetworkInfo info = connectivity.getActiveNetworkInfo();
                if (info != null && info.isConnected()) {
                    if (info.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
        }
        return false;
    }


    public static class NetworkReceiver extends BroadcastReceiver {
        OnNetworkConnectAndLostListener listener;

        public NetworkReceiver(OnNetworkConnectAndLostListener listener) {
            this.listener = listener;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            boolean success = false;
            ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (networkInfo != null && NetworkInfo.State.CONNECTED == networkInfo.getState()) {
                success = true;
            }
            networkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (networkInfo != null && NetworkInfo.State.CONNECTED == networkInfo.getState()){
                success = true;
            }
            if (!success) {
                if(listener != null){
                    listener.onNetworkLost();
                }
            }else {
                if(listener != null){
                    listener.onNetworkConnect();
                }
            }
        }

        public interface OnNetworkConnectAndLostListener {
            void onNetworkLost();
            void onNetworkConnect();
        }
    }
}
