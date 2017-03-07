package com.xogrp.john.music.fragment;

import com.xogrp.john.music.R;

/**
 * Created by john on 03/03/2017.
 */

public class HomeFragment extends AbstractMusicFragment {


    private static final String FRAGMENT_TAG = "fragment_home";

    @Override
    public String getFragmentTag() {
        return FRAGMENT_TAG;
    }

    @Override
    protected int getRootLayout() {
        return R.layout.fragment_home;
    }

}
