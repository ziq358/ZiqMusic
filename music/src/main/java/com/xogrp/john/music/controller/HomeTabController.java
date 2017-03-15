package com.xogrp.john.music.controller;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.TabWidget;

import com.xogrp.john.music.R;

/**
 * Created by john on 14/03/2017.
 */

public class HomeTabController {

    private HorizontalScrollView mTabScrollView;
    public HomeTabController(View view) {
        initTabScrollView(view);
    }

    private void initTabScrollView(View view){
        mTabScrollView = (HorizontalScrollView) view.findViewById(R.id.sv_tab);
    }

}
