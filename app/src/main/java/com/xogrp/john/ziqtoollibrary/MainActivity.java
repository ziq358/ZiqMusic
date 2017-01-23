package com.xogrp.john.ziqtoollibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.xogrp.john.toollibrary.utils.DeviceRelatedUtil;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ziq";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String result = String.valueOf(DeviceRelatedUtil.getStatusBarHeight(this));
        Log.e(TAG, result);
    }
}
