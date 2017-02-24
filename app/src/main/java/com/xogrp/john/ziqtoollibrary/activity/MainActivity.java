package com.xogrp.john.ziqtoollibrary.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xogrp.john.toollibrary.utils.DeviceRelatedUtil;
import com.xogrp.john.toollibrary.utils.ScreenshotManager;
import com.xogrp.john.ziqtoollibrary.R;
import com.xogrp.john.ziqtoollibrary.fragment.HomeFragment;
import com.xogrp.john.ziqtoollibrary.fragment.fragment1;
import com.xogrp.john.ziqtoollibrary.fragment.fragment2;
import com.xogrp.john.ziqtoollibrary.fragment.fragment3;
import com.xogrp.john.ziqtoollibrary.utils.OkHttpNetWorkUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ziq";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.container, new HomeFragment(), "home");
        transaction.commitAllowingStateLoss();
        manager.executePendingTransactions();
    }



}
