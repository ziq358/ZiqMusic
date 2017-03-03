package com.xogrp.john.music.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.xogrp.john.music.R;

/**
 * Created by john on 03/03/2017.
 */

public abstract class DrawerLayoutMusicActivity extends AbstractMusicActivity {


    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);
        onDrawerLayoutCreate();
    }

    abstract void onDrawerLayoutCreate();

    @Override
    public final int getContainer() {
        return R.id.fl_content;
    }
}
