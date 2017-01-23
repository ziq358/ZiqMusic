package com.xogrp.john.toollibrary.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.List;

/**
 * Created by john on 22/01/2017.
 */

public class AppInfoUtil {

    public static String getAppVersionName(Context context){
        String appVersionName = "";
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            appVersionName = packageInfo.versionName;
        }catch (Exception e){
        }
        return appVersionName;
    }

    public static int getAppVersionCode(Context context){
        int appVersionCode = 0;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            appVersionCode = packageInfo.versionCode;
        }catch (Exception e){
        }
        return appVersionCode;
    }

    public static boolean checkInstall(Context context, String packageName){
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packageName,0);
        } catch (PackageManager.NameNotFoundException e) {
        }
        return packageInfo != null;

    }

    public static boolean isAppOnBackground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = context.getPackageName();

        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null)
            return false;

        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            // The name of the process that this object is associated with.
            if (appProcess.processName.equals(packageName)) {
                if(appProcess.importance != ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND){
                    return true;
                }
            }
        }

        return false;
    }

}
