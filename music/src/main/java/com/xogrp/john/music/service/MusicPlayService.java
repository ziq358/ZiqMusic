package com.xogrp.john.music.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Administrator on 2017/3/22.
 */

public class MusicPlayService extends Service {


    private static final String TAG = "ziq";
    private MyBinder mBinder = new MyBinder();
    public class MyBinder extends Binder {
        public MusicPlayService getService() {
             return MusicPlayService.this;
        }
    }

    private Handler logHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate: ");
    }

    String test;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent!= null && intent.hasExtra("test")){
            test = intent.getStringExtra("test");
            Log.e(TAG, "onStartCommand: test = "+ test);
            Log.e(TAG, "onStartCommand: count = "+ count);
        }else{
            Log.e(TAG, "onStartCommand: count = "+ count);
        }

        if(logHandler == null){
            logHandler = new Handler(new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    Log.e(TAG, "MusicPlayService handleMessage: "+msg.what +" test = "+test);
                    if(!stop) logHandler.sendEmptyMessageDelayed(++count, 3000);
                    return false;
                }
            });
        }

        logHandler.sendEmptyMessageDelayed(++count, 3000);

        return super.onStartCommand(intent, flags, startId);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind: ");
        return mBinder;
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy: ");
        super.onDestroy();
    }
    int count = 0;
    public void countPlus(){
        count++;
    }

    public int getCount() {
        return count;
    }
    boolean stop = false;
    public void stopLog(){
        stop = true;
    }
}
