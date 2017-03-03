package com.xogrp.john.music.widget;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MusicDrawerLayout extends DrawerLayout {
	public MusicDrawerLayout(Context context) {
        super(context);
    }

    public MusicDrawerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MusicDrawerLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private boolean mIsDisallowIntercept = false;

    @Override
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        // keep the info about if the innerViews do requestDisallowInterceptTouchEvent
        mIsDisallowIntercept = disallowIntercept;
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // the incorrect array size will only happen in the multi-touch scenario.
        if (ev.getPointerCount() > 1 && mIsDisallowIntercept) {
            requestDisallowInterceptTouchEvent(false);
            boolean isHandled = super.dispatchTouchEvent(ev);
            requestDisallowInterceptTouchEvent(true);
            return isHandled;
        } else {
            return super.dispatchTouchEvent(ev);
        }
    }


}
