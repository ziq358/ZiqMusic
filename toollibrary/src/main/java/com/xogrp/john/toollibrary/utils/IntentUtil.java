package com.xogrp.john.toollibrary.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;

/**
 * Created by john on 23/01/2017.
 */

public class IntentUtil {

    public static final void startApp(Context context, String packageName, Bundle bundle) {
        if (packageName != null) {
            Intent intent = new Intent(Intent.ACTION_MAIN, null);
            intent.setPackage(packageName);
            if(bundle != null){
                intent.putExtras(bundle);
            }
            PackageManager packageManager = context.getPackageManager();
            ResolveInfo resolveInfo = packageManager.queryIntentActivities(intent, 0).iterator().next();
            if(resolveInfo != null){
                String className = resolveInfo.activityInfo.name;
                ComponentName componentName = new ComponentName(packageName, className);
                intent.setComponent(componentName);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        }
    }
}
