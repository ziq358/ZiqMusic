package com.xogrp.john.toollibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;
import android.util.Log;
import android.view.View;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by alliu on 7/28/2016.
 */
public class ScreenshotManager {

    private static final String TAG = "ScreenshotManager";
    private static final String SCREEN_SHOT_PATH = "/planner_screenshot.jpg";
    public static final String DASHBOARE_SCREEN_SHOT_PATH = "/dashboard_screenshot.jpg";
    private static final String SCREEN_SHOT_PATH_FORMAT = "%s%s";
    private Bitmap mCurrentScreenshot;
    private static ScreenshotManager sInstance;
    private static final String THREAD_NAME = "screenshot_thread";
    private ScreenshotThread mSaveScreenshotThread = new ScreenshotThread(THREAD_NAME, Process.THREAD_PRIORITY_BACKGROUND);
    private Handler mHandler;
    private ScreenshotManager(){}
    private Context mApplicationContext;

    public static ScreenshotManager getInstance() {
        if(sInstance == null){
            sInstance = new ScreenshotManager();
            sInstance.mSaveScreenshotThread.start();
            sInstance.mHandler = new Handler(sInstance.mSaveScreenshotThread.getLooper());
        }
        return sInstance;
    }

    public void takeScreenshot(Activity activity){
        generateApplicationContext(activity);
//        String path = activity.getCacheDir().getAbsolutePath() + SCREEN_SHOT_PATH;
        String path = String.format("%s/%s", FileUtil.getPlannerProjectAbsolutePathFile(), "screenshot.jpg");
        View rootView = activity.getWindow().getDecorView().getRootView();
        Bitmap bitmap = generateBitmapFromView(rootView);
        mCurrentScreenshot = bitmap;
        asyncSaveScreenshot(path, bitmap);
    }

    public String takeScreenshot(Activity activity,View rootView,String relativePath){
        generateApplicationContext(activity);
        String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath() + relativePath;
        Bitmap bitmap = generateBitmapFromView(rootView);
        saveScreenshot(absolutePath, bitmap);
        return absolutePath;
    }

    private void generateApplicationContext(Activity activity) {
        if(mApplicationContext == null){
            mApplicationContext = activity.getApplicationContext();
        }
    }

    private class ScreenshotThread extends HandlerThread{

        public ScreenshotThread(String name, int priority) {
            super(name, priority);
        }
    }

    private void asyncSaveScreenshot(final String path, final Bitmap bitmap){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                saveScreenshot(path,bitmap);
            }
        });
    }

    private void saveScreenshot(final String path, final Bitmap bitmap) {
        File imageFile = new File(path);
        FileOutputStream outputStream = null;
        final int quality = 100;
        try {
            outputStream = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            decodeBitmap();
        } catch (IOException e) {
            Log.d(TAG, "takeScreenshot: " + e.toString());
        } finally {
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                }
            }
        }
    }

    public Bitmap getScreenshot(final Activity activity){
        generateApplicationContext(activity);
        decodeBitmap();
        return 	mCurrentScreenshot;
    }

    private void decodeBitmap() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mCurrentScreenshot = BitmapFactory.decodeFile(
                        String.format(SCREEN_SHOT_PATH_FORMAT, mApplicationContext.getCacheDir().getAbsolutePath(), SCREEN_SHOT_PATH));
            }
        });
    }

    public static Bitmap generateBitmapFromView(View view){
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache(true);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache(true));
        view.destroyDrawingCache();
//        view.setDrawingCacheEnabled(false);
        return bitmap;
    }
}
