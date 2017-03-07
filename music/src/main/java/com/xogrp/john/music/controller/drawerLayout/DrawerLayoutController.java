package com.xogrp.john.music.controller.drawerLayout;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import com.xogrp.john.music.R;

/**
 * Created by john on 06/03/2017.
 */

public class DrawerLayoutController {

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private DrawerLayoutContent mDrawerLayoutContent;

    public DrawerLayoutController(DrawerLayout drawerLayout) {
        this.mDrawerLayout = drawerLayout;
        this.mNavigationView = (NavigationView) mDrawerLayout.findViewById(R.id.nav_view);
        this.mDrawerLayoutContent = new DrawerLayoutContent();
        this.mDrawerLayoutContent.initView(this.mNavigationView);
    }

}
