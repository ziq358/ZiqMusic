package com.xogrp.john.music.controller.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.xogrp.john.music.fragment.BaseMusicFragment;
import com.xogrp.john.music.fragment.home.DiscoverFragment;
import com.xogrp.john.music.fragment.home.FriendsFragment;
import com.xogrp.john.music.fragment.home.MusicFragment;

import java.util.ArrayList;

/**
 * Created by john on 16/03/2017.
 */

public class HomeViewPagerController{

    private ViewPager mViewPager;
    ArrayList<BaseMusicFragment> mFragmentArrayList;

    public HomeViewPagerController(ViewPager viewPager, FragmentManager fm) {
        this.mViewPager = viewPager;
        HomeViewPagerAdapter adapter = new HomeViewPagerAdapter(fm, getFragmentArrayList());
        mViewPager.setAdapter(adapter);
    }

    private ArrayList<BaseMusicFragment> getFragmentArrayList(){
        if(mFragmentArrayList == null){
            mFragmentArrayList = new ArrayList<>();
            mFragmentArrayList.add(new DiscoverFragment());
            mFragmentArrayList.add(new MusicFragment());
            mFragmentArrayList.add(new FriendsFragment());
        }
        return mFragmentArrayList;
    }

    public void setCurrentItem(int position, boolean smoothScroll){
        mViewPager.setCurrentItem(position, smoothScroll);
    }

    public void addOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
        mViewPager.addOnPageChangeListener(listener);
    }


    class HomeViewPagerAdapter extends FragmentPagerAdapter{

        private ArrayList<BaseMusicFragment> mMusicFragmentArrayList;


        public HomeViewPagerAdapter(FragmentManager fm, ArrayList<BaseMusicFragment> list) {
            super(fm);
            this.mMusicFragmentArrayList = list;
        }

        @Override
        public Fragment getItem(int position) {
            return mMusicFragmentArrayList == null ? null : mMusicFragmentArrayList.get(position);
        }

        @Override
        public int getCount() {
            return mMusicFragmentArrayList == null ? 0 : mMusicFragmentArrayList.size();
        }
    }

}
