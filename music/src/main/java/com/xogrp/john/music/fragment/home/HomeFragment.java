package com.xogrp.john.music.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.xogrp.john.music.R;
import com.xogrp.john.music.controller.home.HomeTabController;
import com.xogrp.john.music.controller.home.HomeViewPagerController;
import com.xogrp.john.music.fragment.BaseMusicFragment;
import com.xogrp.john.music.service.MusicPlayService;

/**
 * Created by john on 03/03/2017.
 */

public class HomeFragment extends BaseMusicFragment implements HomeTabController.HomeTabListener, ViewPager.OnPageChangeListener {


    private static final String FRAGMENT_TAG = "fragment_home";
    private HomeTabController mTabHostController;
    private HomeViewPagerController mViewPagerController;

    @Override
    public String getFragmentTag() {
        return FRAGMENT_TAG;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        initToolbar(toolbar);

        mTabHostController = new HomeTabController(view);
        mTabHostController.setHomeTabListener(this);

        mViewPagerController = new HomeViewPagerController((ViewPager) view.findViewById(R.id.vp_content), getChildFragmentManager());
        mViewPagerController.addOnPageChangeListener(this);
        mViewPagerController.setCurrentItem(0, false);
        mTabHostController.onPageSelect(0);
    }

    @Override
    public void onDiscoverTab() {
        mViewPagerController.setCurrentItem(0, false);
    }

    @Override
    public void onMusicTab() {
        mViewPagerController.setCurrentItem(1, false);
    }

    @Override
    public void onFriendsTab() {
        mViewPagerController.setCurrentItem(2, false);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        mTabHostController.onPageSelect(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}
