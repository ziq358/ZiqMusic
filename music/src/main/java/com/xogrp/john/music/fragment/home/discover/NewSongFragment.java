package com.xogrp.john.music.fragment.home.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xogrp.john.music.R;
import com.xogrp.john.music.fragment.BaseMusicFragment;

/**
 * Created by john on 16/03/2017.
 */

public class NewSongFragment extends BaseMusicFragment {

    public static final String FRAGMENT_TAG = "new_song_fragment";

    @Override
    public String getFragmentTag() {
        return FRAGMENT_TAG;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_song, container, false);
    }
}
