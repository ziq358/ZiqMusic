package com.xogrp.john.music.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.xogrp.john.music.fragment.AbstractMusicFragment;
import com.xogrp.john.music.listener.FragmentNavigator;

/**
 * Created by john on 03/03/2017.
 */

public abstract class AbstractMusicActivity extends AppCompatActivity implements FragmentNavigator {


    protected FragmentManager mManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mManager = getSupportFragmentManager();
    }

    @Override
    public void addFragment(AbstractMusicFragment fragment) {
        int containId = getContainer();
        if(containId > 0) {
            FragmentTransaction transaction = mManager.beginTransaction();
            transaction.add(containId, fragment, fragment.getFragmentTag());
            transaction.addToBackStack(fragment.getFragmentTag());
            transaction.commitAllowingStateLoss();
            mManager.executePendingTransactions();
        }else{
            Toast.makeText(this, "container id error", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        int fragmentCount = mManager.getBackStackEntryCount();
        AbstractMusicFragment fragment = (AbstractMusicFragment) mManager.findFragmentById(getContainer());
        if(fragment == null){
            onActivityFinished();
        }else if(fragment.onBackPressed()){
            return;
        }else if(fragmentCount <= getBackStackCountToFinished()){
            onActivityFinished();
        }else{
            mManager.popBackStackImmediate();
        }
    }

    protected int getBackStackCountToFinished(){
        return 1;
    }

    public void onActivityFinished(){
        finish();
    }


    /**
     *
     * bottom player
     * */

    protected boolean isBottomPlayerShow(){
        return false;
    }


}
