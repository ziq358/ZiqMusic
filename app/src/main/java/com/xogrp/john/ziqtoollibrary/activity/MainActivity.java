package com.xogrp.john.ziqtoollibrary.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xogrp.john.ziqtoollibrary.R;
import com.xogrp.john.ziqtoollibrary.fragment.HomeFragment;

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
