package com.xogrp.john.music.controller.home;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.xogrp.john.music.R;
import com.xogrp.john.music.listener.Drawer;

/**
 * Created by john on 14/03/2017.
 */

public class HomeTabController {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private HomeTabListener mHomeTabListener;
    LinearLayout mTabLinearLayout;
    public HomeTabController(View view) {
        initTabScrollView(view);
    }

    private void initTabScrollView(View view){
        mContext = view.getContext();
        mLayoutInflater = LayoutInflater.from(mContext);
        mTabLinearLayout = (LinearLayout) view.findViewById(R.id.sv_ll_tab);
        TabInfo discoverTabInfo = new DiscoverTabInfo();
        TabInfo musicTabInfo = new MusicTabInfo();
        TabInfo friendsTabInfo = new FriendsTabInfo();
        mTabLinearLayout.addView(discoverTabInfo.createView(mTabLinearLayout));
        mTabLinearLayout.addView(musicTabInfo.createView(mTabLinearLayout));
        mTabLinearLayout.addView(friendsTabInfo.createView(mTabLinearLayout));
    }

    public void onPageSelect(int position){
        int count = mTabLinearLayout.getChildCount();
        for (int i = 0; i < count; i++) {
            View view = mTabLinearLayout.getChildAt(i);
            if(view != null){
                view.setSelected(i == position);
            }
        }
    }


    private abstract class TabInfo implements View.OnClickListener{
        protected View createView(ViewGroup parentView) {
            ImageView img = (ImageView) mLayoutInflater.inflate(R.layout.home_toolbar_tab_item, parentView, false);
            Drawable drawable = ContextCompat.getDrawable(mContext,getDrawableId());
            DrawableCompat.setTintList(drawable, ContextCompat.getColorStateList(mContext, R.color.selector_home_tab_item_icon));
            ViewCompat.setBackground(img, drawable);
            img.setOnClickListener(this);
            return img;
        }

        abstract int getDrawableId();
    }

    private class DiscoverTabInfo extends TabInfo{

        @Override
        public void onClick(View view) {
            if(mHomeTabListener != null){
                mHomeTabListener.onDiscoverTab();
            }
        }

        @Override
        int getDrawableId() {
            return R.mipmap.actionbar_discover_selected;
        }
    }

    private class MusicTabInfo extends TabInfo{

        @Override
        public void onClick(View view) {
            if(mHomeTabListener != null){
                mHomeTabListener.onMusicTab();
            }
        }

        @Override
        int getDrawableId() {
            return R.mipmap.actionbar_music_selected;
        }
    }

    private class FriendsTabInfo extends TabInfo{

        @Override
        public void onClick(View view) {
            if(mHomeTabListener != null){
                mHomeTabListener.onFriendsTab();
            }
        }

        @Override
        int getDrawableId() {
            return R.mipmap.actionbar_friends_selected;
        }
    }

    public interface HomeTabListener{
        void onDiscoverTab();
        void onMusicTab();
        void onFriendsTab();
    }

    public void setHomeTabListener(HomeTabListener homeTabListener) {
        this.mHomeTabListener = homeTabListener;
    }
}
