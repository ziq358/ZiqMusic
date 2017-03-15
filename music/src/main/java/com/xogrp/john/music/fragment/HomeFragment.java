package com.xogrp.john.music.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.xogrp.john.music.R;
import com.xogrp.john.music.controller.HomeTabController;

/**
 * Created by john on 03/03/2017.
 */

public class HomeFragment extends BaseMusicFragment implements HomeTabController.HomeTabListener {


    private static final String FRAGMENT_TAG = "fragment_home";

    @Override
    public String getFragmentTag() {
        return FRAGMENT_TAG;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        initToolbar(toolbar);
        HomeTabController tabHostController = new HomeTabController(view);
        tabHostController.setHomeTabListener(this);
    }

    @Override
    public void onDiscoverTab() {
        Toast.makeText(getContext(), "discover", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMusicTab() {
        Toast.makeText(getContext(), "music", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFriendsTab() {
        Toast.makeText(getContext(), "friends", Toast.LENGTH_SHORT).show();
    }
}
