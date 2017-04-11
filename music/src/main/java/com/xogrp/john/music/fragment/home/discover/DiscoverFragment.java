package com.xogrp.john.music.fragment.home.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xogrp.john.music.R;
import com.xogrp.john.music.fragment.BaseMusicFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by john on 16/03/2017.
 */

public class DiscoverFragment extends BaseMusicFragment {

    public static final String FRAGMENT_TAG = "discover_fragment";
    @BindView(R.id.discover_tabs)
    TabLayout mTabLayout;

    @BindView(R.id.discover_viewpager)
    ViewPager mViewPager;

    @BindString(R.string.s_new_song)
    String mNewSong;
    @BindString(R.string.s_song_list)
    String mSongList;
    @BindString(R.string.s_ranking_list)
    String mRankingList;


    @Override
    public String getFragmentTag() {
        return FRAGMENT_TAG;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_discover, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DiscoverFragmentViewPagerAdapter adapter = new DiscoverFragmentViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new NewSongFragment(), mNewSong);
        adapter.addFragment(new SongListFragment(), mSongList);
        adapter.addFragment(new RankingListFragment(), mRankingList);
        mViewPager.setAdapter(adapter);

        mTabLayout.setupWithViewPager(mViewPager);
    }

    private class DiscoverFragmentViewPagerAdapter extends FragmentStatePagerAdapter{

        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public DiscoverFragmentViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }

}
