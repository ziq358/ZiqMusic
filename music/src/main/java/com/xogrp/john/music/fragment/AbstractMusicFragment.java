package com.xogrp.john.music.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.xogrp.john.music.activity.AbstractMusicActivity;
import com.xogrp.john.music.listener.ChildFragmentNavigator;
import com.xogrp.john.music.listener.FragmentNavigator;

/**
 * Created by john on 03/03/2017.
 */

public abstract class AbstractMusicFragment extends Fragment implements ChildFragmentNavigator{

    public abstract String getFragmentTag();
    protected FragmentNavigator mFragmentNavigator;
    protected FragmentManager mChildFragmentManager;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof AbstractMusicActivity){
            mFragmentNavigator = (FragmentNavigator) context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mChildFragmentManager = getChildFragmentManager();
    }

    public FragmentNavigator getActivityFragmentNavigator(){
        return mFragmentNavigator;
    }


    @Override
    public int getChildContainer() {
        return 0;
    }

    @Override
    public void addChildFragment(AbstractMusicFragment fragment) {
        addChildFragment(getChildContainer(), fragment);
    }

    @Override
    public void addChildFragment(int container, AbstractMusicFragment fragment) {
        if(container > 0) {
            FragmentTransaction transaction = mChildFragmentManager.beginTransaction();
            transaction.add(container, fragment, fragment.getFragmentTag());
            transaction.addToBackStack(fragment.getFragmentTag());
            transaction.commitAllowingStateLoss();
            mChildFragmentManager.executePendingTransactions();
        }else{
            Toast.makeText(getContext(), "container id error", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean onBackPressed() {
        return false;
    }

}
