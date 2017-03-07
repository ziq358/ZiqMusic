package com.xogrp.john.music.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;

import com.xogrp.john.music.R;
import com.xogrp.john.music.controller.drawerLayout.DrawerLayoutController;

/**
 * Created by john on 03/03/2017.
 */

public abstract class DrawerLayoutMusicActivity extends AbstractMusicActivity implements DrawerLayoutController.LeftNavigator {

    DrawerLayoutController mDrawerLayoutController;

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);
        initView();
        onDrawerLayoutCreate();
    }

    private void initView() {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayoutController = new DrawerLayoutController(drawerLayout, this);
    }

    abstract void onDrawerLayoutCreate();

    @Override
    public final int getContainer() {
        return R.id.fl_content;
    }

    @Override
    public void gotoMyMessage() {

    }
}
