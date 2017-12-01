package com.xogrp.john.ziqtoollibrary.activity;

import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;

import com.xogrp.john.ziqtoollibrary.R;
import com.xogrp.john.ziqtoollibrary.fragment.HomeFragment;
import com.xogrp.john.ziqtoollibrary.statusBar.StatusBarUtil;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ziq";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StatusBarUtil.setUpStatusBar(this);

        setContentView(R.layout.activity_main_drawer);


        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);


        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.container, new HomeFragment(), "home");
        transaction.commitAllowingStateLoss();
        manager.executePendingTransactions();
    }



}
