package com.xogrp.john.toollibrary.utils.customTabUtils;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsServiceConnection;
import android.support.customtabs.CustomTabsSession;

import com.xogrp.john.toollibrary.R;

import java.util.List;

/**
 * Created by jwu on 12/15/2016.
 */

public class CustomTabHelper implements ServiceConnectionCallback {

    private CustomTabsSession mCustomTabsSession;
    private CustomTabsClient mClient;
    private CustomTabsServiceConnection mConnection;
    private static CustomTabHelper sInstance;

    private CustomTabHelper() {
    }

    public static CustomTabHelper getInstance() {
        if(sInstance == null){
            sInstance = new CustomTabHelper();
        }
        return sInstance;
    }

    public void bindCustomTabsService(Activity activity) {
        if (mClient != null) return;

        String packageName = CustomTabsClassHelper.getPackageNameToUse(activity);
        if (packageName == null) return;

        mConnection = new ServiceConnection(this);
        CustomTabsClient.bindCustomTabsService(activity, packageName, mConnection);
    }

    public void unbindCustomTabsService(Activity activity) {
        if (mConnection == null) return;
        activity.unbindService(mConnection);
        mClient = null;
        mCustomTabsSession = null;
        mConnection = null;
    }

    @Override
    public void onServiceConnected(CustomTabsClient client) {
        mClient = client;
        mClient.warmup(0L);
    }

    @Override
    public void onServiceDisconnected() {
        mClient = null;
        mCustomTabsSession = null;
    }


    public interface CustomTabFallback {
        /**
         *
         * @param activity The Activity that wants to open the Uri.
         * @param uri The uri to be opened by the fallback.
         */
        void openUri(Activity activity, Uri uri);
    }

    public static void openCustomTab(Activity activity, final String url) {
        openCustomTab(activity, url);
    }

    public static void openCustomTab(Activity activity, final String url,CustomTabFallback fallback) {
        if(activity != null){
            CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();
            intentBuilder.setShowTitle(true);
            intentBuilder.setCloseButtonIcon(BitmapFactory.decodeResource(activity.getResources(), R.drawable.back_black));
            CustomTabsIntent customTabsIntent = intentBuilder.build();
            openCustomTab(activity,customTabsIntent, Uri.parse(url),fallback);
        }
    }

    public static void openCustomTab(Activity activity,
                                     CustomTabsIntent customTabsIntent,
                                     Uri uri,
                                     CustomTabFallback fallback) {
        try {
            String packageName = CustomTabsClassHelper.getPackageNameToUse(activity);
            if (packageName == null) {
                if (fallback != null) {
                    fallback.openUri(activity, uri);
                }
            } else {
                customTabsIntent.intent.setPackage(packageName);
                customTabsIntent.launchUrl(activity, uri);
            }
        }catch (Exception e){
            if (fallback != null) {
                fallback.openUri(activity, uri);
            }
        }

    }

    public boolean mayLaunchUrl(Uri uri, Bundle extras, List<Bundle> otherLikelyBundles) {
        if (mClient == null) return false;

        CustomTabsSession session = getSession();
        if (session == null) return false;

        return session.mayLaunchUrl(uri, extras, otherLikelyBundles);
    }

    public CustomTabsSession getSession() {
        if (mClient == null) {
            mCustomTabsSession = null;
        } else if (mCustomTabsSession == null) {
            mCustomTabsSession = mClient.newSession(null);
        }
        return mCustomTabsSession;
    }

}
