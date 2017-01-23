package com.xogrp.john.toollibrary.utils;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.provider.Settings;

/**
 * Created by john on 22/01/2017.
 */

public class DeviceRelatedUtil {



    /**
    *   有时候手机的动画配置关掉的话会影响到，app中动画的执行。
    * */
    public static boolean isAnimatorTurnOff(Context context){

        float factor = 1f;
        try {
            if(Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
                //从contentProvider中获得
                factor = Settings.System.getFloat(context.getContentResolver(), Settings.System.ANIMATOR_DURATION_SCALE);
            }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
                factor = Settings.Global.getFloat(context.getContentResolver(), Settings.Global.ANIMATOR_DURATION_SCALE);
            }
        } catch (Settings.SettingNotFoundException e) {
        }
        return factor == 0.0f;
    }

    public static int getDeviceHeight(Context context){
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getDeviceWidth(Context context){
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getStatusBarHeight(Context context){
        int statusBarHeight = 0;
        Resources resources = context.getResources();
        if(resources != null){
            int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
            if(resourceId > 0){
                statusBarHeight = resources.getDimensionPixelSize(resourceId);
            }
        }
        return statusBarHeight;
    }
}
