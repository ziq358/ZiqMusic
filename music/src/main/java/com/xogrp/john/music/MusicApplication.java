package com.xogrp.john.music;

import android.app.Application;

import com.xogrp.john.music.utils.LogUtil;

/**
 * Created by john on 03/03/2017.
 */

public class MusicApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.e("MusicApplication onCreate");
    }
}
