package com.xogrp.john.music.controller.drawerLayout;

import android.support.design.widget.NavigationView;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.xogrp.john.music.R;

/**
 * Created by john on 06/03/2017.
 */

public class DrawerLayoutContent {

    private LayoutInflater mLayoutInflater;
    private final int content_header_layout = R.layout.left_nav_header;
    private final int content_menu_layout =   R.menu.left_nav_menu;
    private final int content_footer_layout = R.layout.left_nav_footer;

    public void initView(NavigationView navigationView){
        mLayoutInflater = LayoutInflater.from(navigationView.getContext());
        navigationView.inflateMenu(content_menu_layout);
        navigationView.inflateHeaderView(content_header_layout);
        FrameLayout footer = (FrameLayout) navigationView.findViewById(R.id.nav_view_footer);
        if(footer != null){
            footer.addView(mLayoutInflater.inflate(content_footer_layout, navigationView, false));
        }
    }


}
