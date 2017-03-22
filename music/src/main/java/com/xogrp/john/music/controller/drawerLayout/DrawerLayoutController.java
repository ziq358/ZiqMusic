package com.xogrp.john.music.controller.drawerLayout;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;

import com.xogrp.john.music.R;
import com.xogrp.john.music.service.MusicPlayService;

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

    int count;
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch(item.getItemId()){
            case R.id.left_nav_my_msg:
                mLeftNavigator.gotoMyMessage();
                intent = new Intent(mDrawerLayout.getContext(),MusicPlayService.class);
                intent.putExtra("test"," "+ count++);
                mDrawerLayout.getContext().startService(intent);
                break;
            case R.id.left_nav_vip_center:
                intent = new Intent(mDrawerLayout.getContext(),MusicPlayService.class);
                mDrawerLayout.getContext().stopService(intent);
                break;
            case R.id.left_nav_shopping_mall:
                intent = new Intent(mDrawerLayout.getContext(),MusicPlayService.class);
                mDrawerLayout.getContext().bindService(intent, new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                        Log.e("ziq", "bindService onServiceConnected: ");
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName componentName) {
                        Log.e("ziq", "bindService onServiceDisconnected: ");
                    }
                }, 0);
                break;
            case R.id.left_nav_listen_online:
                mDrawerLayout.getContext().unbindService(new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                        Log.e("ziq", "unbindService onServiceConnected: ");
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName componentName) {
                        Log.e("ziq", "unbindService onServiceDisconnected: ");
                    }
                });
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


    public void openDrawer(){
        mDrawerLayout.openDrawer(GravityCompat.START);
    }

    public void closeDrawer(){
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }

}
