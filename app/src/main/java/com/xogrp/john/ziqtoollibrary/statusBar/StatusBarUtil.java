package com.xogrp.john.ziqtoollibrary.statusBar;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by john on 29/11/2017.
 */

public class StatusBarUtil {

    public static void setUpStatusBar(Activity activity){
        Window window = activity.getWindow();

        // for 4.4
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            // 内容显示在status bar 下面。。即全屏，，但在4.4 statusbar 没有透明
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        // for 5.0 +
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 内容显示在status bar 下面。。即全屏
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            // status 透明
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }


    }

    public static void setUpFullScreen(Activity activity){
        // 全屏， statusBar 看不到。。拖一下会出来。。然后消失
        Window window = activity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // 全屏显示， 拖出statusBar 后退出了全屏
//        View decorView = window.getDecorView();
//        // Hide the status bar.
//        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

    }

}
