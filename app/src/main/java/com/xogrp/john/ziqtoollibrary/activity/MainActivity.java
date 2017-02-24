package com.xogrp.john.ziqtoollibrary.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xogrp.john.toollibrary.utils.DeviceRelatedUtil;
import com.xogrp.john.ziqtoollibrary.R;
import com.xogrp.john.ziqtoollibrary.utils.OkHttpNetWorkUtil;

import java.io.IOException;

import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ziq";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        OkHttpNetWorkUtil.login("xiaoqiang33@xogrp.com", "123456");
//                    }
//                }).start();
                OkHttpNetWorkUtil.login("xiaoqiang33@xogrp.com", "123456");
                Log.e(TAG, "onClick: ");
            }
        });
    }

}
