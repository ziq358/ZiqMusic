package com.xogrp.john.toollibrary.utils;

import android.os.Build;

/**
 * Created by john on 22/01/2017.
 */

public class SDKVersionUtil {

    public static boolean isLessThan(int targetSDKVersion){
        return Build.VERSION.SDK_INT < targetSDKVersion;
    }

    public static boolean isGreaterThan(int targetSDKVersion){
        return Build.VERSION.SDK_INT > targetSDKVersion;
    }

    public static boolean isEqualTo(int targetSDKVersion){
        return Build.VERSION.SDK_INT == targetSDKVersion;
    }

    public static boolean is(int targetSDKVersion){
        return Build.VERSION.SDK_INT == targetSDKVersion;
    }

    public static boolean isLessThanOrEqualTo(int targetSDKVersion){
        return Build.VERSION.SDK_INT <= targetSDKVersion;
    }

    public static boolean isGreaterThanOrEqualTo(int targetSDKVersion){
        return Build.VERSION.SDK_INT >= targetSDKVersion;
    }


    public static boolean isGreaterThanOrEqualTo21(){
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

}
