package com.xogrp.john.music.activity;

import android.os.Bundle;

import com.xogrp.john.music.R;
import com.xogrp.john.music.fragment.HomeFragment;

public class MainActivity extends DrawerLayoutMusicActivity {

    @Override
    void onDrawerLayoutCreate() {
        addFragment(new HomeFragment());
    }
}
