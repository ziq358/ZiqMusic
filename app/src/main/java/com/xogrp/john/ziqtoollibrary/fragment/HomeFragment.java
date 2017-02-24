package com.xogrp.john.ziqtoollibrary.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xogrp.john.ziqtoollibrary.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by john on 24/02/2017.
 */

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.homefragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        List<Fragment> list = new ArrayList<>();
        list.add(new fragment1());
        list.add(new fragment2());
        list.add(new fragment3());
        FragmentStatePagerAdapter adapter = new VenueSortFragmentPageAdapter(getFragmentManager(), list);
        viewPager.setAdapter(adapter);
    }

    private static class VenueSortFragmentPageAdapter extends FragmentStatePagerAdapter {
        private List<Fragment> mFragments;

        public VenueSortFragmentPageAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.mFragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }
}
