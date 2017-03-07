package com.xogrp.john.music.controller.drawerLayout;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.xogrp.john.music.R;

/**
 * Created by john on 06/03/2017.
 */

public class DrawerLayoutController implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private DrawerLayoutContent mDrawerLayoutContent;
    private LeftNavigator mLeftNavigator;

    public DrawerLayoutController(DrawerLayout drawerLayout, LeftNavigator leftNavigator) {
        this.mDrawerLayout = drawerLayout;
        this.mLeftNavigator = leftNavigator;
        this.mNavigationView = (NavigationView) mDrawerLayout.findViewById(R.id.nav_view);
        this.mDrawerLayoutContent = new DrawerLayoutContent();
        this.mDrawerLayoutContent.initView(this.mNavigationView);

        this.mNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.left_nav_my_msg:
                mLeftNavigator.gotoMyMessage();
                break;
            case R.id.left_nav_vip_center:
                break;
            case R.id.left_nav_shopping_mall:
                break;
            case R.id.left_nav_listen_online:
                break;
            case R.id.left_nav_listen_song:
                break;
            case R.id.left_nav_theme_skin:
                break;
            case R.id.left_nav_night_model:
                break;
            case R.id.left_nav_timer_to_stop:
                break;
            case R.id.left_nav_scan:
                break;
            case R.id.left_nav_my_cloud:
                break;
            case R.id.left_nav_music_alarm_clock:
                break;
            case R.id.left_nav_drive_model:
                break;
        }

        return false;
    }

    public interface LeftNavigator {
        void gotoMyMessage();
    }


}
