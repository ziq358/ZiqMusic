package com.xogrp.john.music.fragment.home.discover;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recycleviewrelatedlibrary.BaseRecycleViewAdapter;
import com.example.recycleviewrelatedlibrary.BaseRecycleViewViewHolder;
import com.example.recycleviewrelatedlibrary.BaseRecycleViewViewType;
import com.xogrp.john.music.R;
import com.xogrp.john.music.fragment.BaseMusicFragment;

import java.util.List;

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
    }

    private class RecommendAdapter extends BaseRecycleViewAdapter<String>{

        public RecommendAdapter(Context context) {
            super(context);
        }

        @Override
        protected void initViewType(List mViewTypes) {

        }

        private class HeaderViewType extends BaseRecycleViewViewType{

            @Override
            protected boolean isMatchViewType(int position) {
                return false;
            }

            @Override
            public int getItemViewType() {
                return 0;
            }

            @Override
            protected int getLayoutRes() {
                return 0;
            }

            @Override
            protected void onBindViewHolder(BaseRecycleViewViewHolder holder, int position) {

            }
        }

    }

}
