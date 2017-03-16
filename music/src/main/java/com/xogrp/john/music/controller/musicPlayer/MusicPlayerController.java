package com.xogrp.john.music.controller.musicPlayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xogrp.john.music.R;

/**
 * Created by john on 16/03/2017.
 */

public class MusicPlayerController {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private View mRootView;

    public MusicPlayerController(ViewGroup parentView) {
        this.mContext = parentView.getContext();
        this.mLayoutInflater = LayoutInflater.from(mContext);
        this.mRootView = mLayoutInflater.inflate(R.layout.music_player_bottom_layout, parentView, false);
        parentView.addView(mRootView);
    }

    public View getView(){
        return mRootView;
    }
}
