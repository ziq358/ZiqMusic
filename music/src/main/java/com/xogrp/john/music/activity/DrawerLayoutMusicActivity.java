package com.xogrp.john.music.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.xogrp.john.music.R;
import com.xogrp.john.music.controller.drawerLayout.DrawerLayoutController;
import com.xogrp.john.music.controller.musicPlayer.MusicPlayerController;
import com.xogrp.john.music.listener.Drawer;

/**
 * Created by john on 03/03/2017.
 */

public abstract class DrawerLayoutMusicActivity extends AbstractMusicActivity implements DrawerLayoutController.LeftNavigator, Drawer {

    DrawerLayoutController mDrawerLayoutController;
    MusicPlayerController musicPlayerController;

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);
        initDrawerLayoutView();
        onMusicCreate();
    }

    private void initDrawerLayoutView() {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayoutController = new DrawerLayoutController(drawerLayout, this);
        musicPlayerController = new MusicPlayerController((ViewGroup) findViewById(R.id.fl_bottom_player), this);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.e("ziq", "Activity onRequestPermissionsResult: ");
        if(musicPlayerController != null){
            musicPlayerController.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    abstract void onMusicCreate();

    @Override
    public final int getContainer() {
        return R.id.fl_content;
    }

    @Override
    public void gotoMyMessage() {

    }

    @Override
    public void openDrawer() {
        mDrawerLayoutController.openDrawer();
    }

    @Override
    public void closeDrawer() {
        mDrawerLayoutController.closeDrawer();
    }
}
