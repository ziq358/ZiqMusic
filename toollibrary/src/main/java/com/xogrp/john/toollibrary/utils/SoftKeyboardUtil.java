package com.xogrp.john.toollibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;

import java.util.ArrayList;
import java.util.List;

public class SoftKeyboardUtil {

    private ArrayMap<Activity, List<OnSoftKeyBoardGlobalLayoutListener>> mOnGlobalLayoutListenerMap = new ArrayMap<>();

    private static SoftKeyboardUtil sInstance = new SoftKeyboardUtil();
    private SoftKeyboardUtil(){}

    public static SoftKeyboardUtil getInstance(){
        return sInstance;
    }


    public static void showSoftKeyboard(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(view, 0);
    }

    public static void hideSoftKeyboard(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void showKeyboard(Context context){
        InputMethodManager inputMethodManager = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if(inputMethodManager != null && !inputMethodManager.isActive()){
            inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public static void hideKeyboard(Context context){
        InputMethodManager inputMethodManager = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if(inputMethodManager != null && inputMethodManager.isActive()){
            inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public static void hideKeyboard(Activity activity){
        InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null && imm.isActive()) {
            try {
                imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),	InputMethodManager.HIDE_NOT_ALWAYS);
            } catch (Exception e) {
            }
        }
    }


    public void addSoftKeyBoardVisibilityListener(@NonNull Activity activity, SoftKeyBoardVisibilityListener listener) {
        if(listener != null) {
            final View decorView = activity.getWindow().getDecorView();
            if (decorView != null) {
                List<OnSoftKeyBoardGlobalLayoutListener> onSoftKeyBoardGlobalLayoutListeners = mOnGlobalLayoutListenerMap.get(activity);
                OnSoftKeyBoardGlobalLayoutListener onSoftKeyBoardGlobalLayoutListener = new OnSoftKeyBoardGlobalLayoutListener(activity, decorView, listener);
                if(onSoftKeyBoardGlobalLayoutListeners == null){
                    onSoftKeyBoardGlobalLayoutListeners = new ArrayList<>();
                    mOnGlobalLayoutListenerMap.put(activity, onSoftKeyBoardGlobalLayoutListeners);
                }
                onSoftKeyBoardGlobalLayoutListeners.add(onSoftKeyBoardGlobalLayoutListener);
                decorView.getViewTreeObserver().addOnGlobalLayoutListener(onSoftKeyBoardGlobalLayoutListener);
            }
        }
    }

    public void removeAllOnGlobalLayoutListener(Activity activity){
        List<OnSoftKeyBoardGlobalLayoutListener> onGlobalLayoutListeners = mOnGlobalLayoutListenerMap.get(activity);
        final View decorView = activity.getWindow().getDecorView();
        if(onGlobalLayoutListeners != null) {
            for (OnSoftKeyBoardGlobalLayoutListener listener : onGlobalLayoutListeners) {
                if (listener != null && decorView != null) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        decorView.getViewTreeObserver().removeOnGlobalLayoutListener(listener);
                    } else {
                        decorView.getViewTreeObserver().removeGlobalOnLayoutListener(listener);
                    }
                }
            }
        }
    }

    public void removeOnGlobalLayoutListener(Activity activity, SoftKeyBoardVisibilityListener removeListener){
        List<OnSoftKeyBoardGlobalLayoutListener> onGlobalLayoutListeners = mOnGlobalLayoutListenerMap.get(activity);
        final View decorView = activity.getWindow().getDecorView();
        if(onGlobalLayoutListeners != null) {
            for (OnSoftKeyBoardGlobalLayoutListener listener : onGlobalLayoutListeners) {
                if (listener == removeListener && decorView != null) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        decorView.getViewTreeObserver().removeOnGlobalLayoutListener(listener);
                    } else {
                        decorView.getViewTreeObserver().removeGlobalOnLayoutListener(listener);
                    }
                }
            }
        }
    }

    private static class OnSoftKeyBoardGlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener{
        private final Context mContext;
        private boolean mIsSoftKeyboardVisible;
        private View mView;
        private SoftKeyBoardVisibilityListener mSoftKeyBoardVisibilityListener;

        OnSoftKeyBoardGlobalLayoutListener(Context context, View view, SoftKeyBoardVisibilityListener listener){
            mView = view;
            mSoftKeyBoardVisibilityListener = listener;
            mContext = context;
        }

        @Override
        public void onGlobalLayout() {
            Rect r = new Rect();
            mView.getWindowVisibleDisplayFrame(r);
            int screenHeight = mView.getRootView().getHeight();
            int heightDifference = screenHeight - r.bottom;
            boolean visible = heightDifference > screenHeight / 3;
            if ((visible && !mIsSoftKeyboardVisible)) {
                mIsSoftKeyboardVisible = true;
                int statusBarHeight = SDKVersionUtil.isGreaterThanOrEqualTo(21)? DeviceRelatedUtil.getStatusBarHeight(mContext) : - DeviceRelatedUtil.getStatusBarHeight(mContext);
                mSoftKeyBoardVisibilityListener.softKeyboardVisible(heightDifference - statusBarHeight);
            } else if (!visible && mIsSoftKeyboardVisible) {
                mIsSoftKeyboardVisible = false;
                mSoftKeyBoardVisibilityListener.softKeyboardGone();
            }
        }
    }

    public interface SoftKeyBoardVisibilityListener{
        void softKeyboardVisible(int softKeyboardHeight);
        void softKeyboardGone();
    }
}
