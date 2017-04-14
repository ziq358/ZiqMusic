package com.xogrp.john.music.fragment.home.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xogrp.john.music.R;
import com.xogrp.john.music.adapter.RecommendAdapter;
import com.xogrp.john.music.fragment.BaseMusicFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by john on 16/03/2017.
 */

public class RecommendFragment extends BaseMusicFragment {

    public static final String FRAGMENT_TAG = "new_song_fragment";

    @BindView(R.id.rv_new_song)
    RecyclerView mRecyclerView;

    @Override
    public String getFragmentTag() {
        return FRAGMENT_TAG;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_song, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        RecommendAdapter adapter = new RecommendAdapter(getContext());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);
    }



}
