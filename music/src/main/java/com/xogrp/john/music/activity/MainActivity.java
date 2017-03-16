package com.xogrp.john.music.activity;

import com.xogrp.john.music.fragment.home.HomeFragment;

public class MainActivity extends DrawerLayoutMusicActivity {

    @Override
    void onMusicCreate() {
        addFragment(new HomeFragment());
    }
}
