package com.xogrp.john.music.adapter;

import android.content.Context;

import com.example.recycleviewrelatedlibrary.BaseRecycleViewAdapter;
import com.example.recycleviewrelatedlibrary.BaseRecycleViewViewHolder;
import com.example.recycleviewrelatedlibrary.BaseRecycleViewViewType;
import com.xogrp.john.music.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by john on 14/04/2017.
 */

public class RecommendAdapter extends BaseRecycleViewAdapter<String> {

    private final static String AUTO_ROLL_VIEW_TYPE = "auto_roll_view_type";
    private final static String THREE_CIRCLE_VIEW_TYPE = "three_circle_view_type";
    private final static String RECOMMEND_SONG_LIST_VIEW_TYPE = "recommend_song_list_view_type";
    private final static String EXCLUSIVE_SEND_OUT_VIEW_TYPE = "exclusive_send_out_view_type";
    private final static String NEWEST_MUSIC_VIEW_TYPE = "newest_music_view_type";
    private final static String RECOMMEND_MV_VIEW_TYPE = "recommend_mv_view_type";
    private final static String RADIO_VIEW_TYPE = "radio_view_type";
    private final static String ADJUST_ORDER_VIEW_TYPE = "adjust_order_view_type";

    private final static int INT_AUTO_ROLL_VIEW_TYPE = 0;
    private final static int INT_THREE_CIRCLE_VIEW_TYPE = 1;
    private final static int INT_RECOMMEND_SONG_LIST_VIEW_TYPE = 2;
    private final static int INT_EXCLUSIVE_SEND_OUT_VIEW_TYPE = 3;
    private final static int INT_NEWEST_MUSIC_VIEW_TYPE = 4;
    private final static int INT_RECOMMEND_MV_VIEW_TYPE = 5;
    private final static int INT_RADIO_VIEW_TYPE = 6;
    private final static int INT_ADJUST_ORDER_VIEW_TYPE = 7;

    private List<String> mDataList = new ArrayList<>();

    public RecommendAdapter(Context context) {
        super(context);
        mDataList.add(AUTO_ROLL_VIEW_TYPE);
        mDataList.add(THREE_CIRCLE_VIEW_TYPE);
        mDataList.add(RECOMMEND_SONG_LIST_VIEW_TYPE);
        mDataList.add(EXCLUSIVE_SEND_OUT_VIEW_TYPE);
        mDataList.add(NEWEST_MUSIC_VIEW_TYPE);
        mDataList.add(RECOMMEND_MV_VIEW_TYPE);
        mDataList.add(RADIO_VIEW_TYPE);
        mDataList.add(ADJUST_ORDER_VIEW_TYPE);
        setData(mDataList);
    }

    @Override
    protected void initViewType(List<BaseRecycleViewViewType> viewTypesList) {
        viewTypesList.add(new AutoRollViewType(this));
        viewTypesList.add(new ThreeCircleViewType(this));
        viewTypesList.add(new RecommendSongListViewType(this));
        viewTypesList.add(new ExclusiveSendOutViewType(this));
        viewTypesList.add(new NewestMusicViewType(this));
        viewTypesList.add(new RecommendMvViewType(this));
        viewTypesList.add(new RadioViewType(this));
        viewTypesList.add(new AdjustOrderViewType(this));
    }

    private class AutoRollViewType extends BaseRecycleViewViewType{

        public AutoRollViewType(BaseRecycleViewAdapter adapter) {
            super(adapter);
        }

        @Override
        protected boolean isMatchViewType(int position) {
            return position == getItemViewType();
        }

        @Override
        public int getItemViewType() {
            return INT_AUTO_ROLL_VIEW_TYPE;
        }

        @Override
        protected int getLayoutRes() {
            return R.layout.auto_roll_layout;
        }

        @Override
        protected void onBindViewHolder(BaseRecycleViewViewHolder holder, int position) {

        }
    }

    private class ThreeCircleViewType extends BaseRecycleViewViewType{

        public ThreeCircleViewType(BaseRecycleViewAdapter adapter) {
            super(adapter);
        }

        @Override
        protected boolean isMatchViewType(int position) {
            return position == getItemViewType();
        }

        @Override
        public int getItemViewType() {
            return INT_THREE_CIRCLE_VIEW_TYPE;
        }

        @Override
        protected int getLayoutRes() {
            return R.layout.three_circle_layout;
        }

        @Override
        protected void onBindViewHolder(BaseRecycleViewViewHolder holder, int position) {

        }
    }

    private class RecommendSongListViewType extends BaseRecycleViewViewType{

        public RecommendSongListViewType(BaseRecycleViewAdapter adapter) {
            super(adapter);
        }

        @Override
        protected boolean isMatchViewType(int position) {
            return position == getItemViewType();
        }

        @Override
        public int getItemViewType() {
            return INT_RECOMMEND_SONG_LIST_VIEW_TYPE;
        }

        @Override
        protected int getLayoutRes() {
            return R.layout.recommend_song_list_layout;
        }

        @Override
        protected void onBindViewHolder(BaseRecycleViewViewHolder holder, int position) {

        }
    }

    private class ExclusiveSendOutViewType extends BaseRecycleViewViewType{

        public ExclusiveSendOutViewType(BaseRecycleViewAdapter adapter) {
            super(adapter);
        }

        @Override
        protected boolean isMatchViewType(int position) {
            return position == getItemViewType();
        }

        @Override
        public int getItemViewType() {
            return INT_EXCLUSIVE_SEND_OUT_VIEW_TYPE;
        }

        @Override
        protected int getLayoutRes() {
            return R.layout.exclusive_send_out_layout;
        }

        @Override
        protected void onBindViewHolder(BaseRecycleViewViewHolder holder, int position) {

        }
    }

    private class NewestMusicViewType extends BaseRecycleViewViewType{

        public NewestMusicViewType(BaseRecycleViewAdapter adapter) {
            super(adapter);
        }

        @Override
        protected boolean isMatchViewType(int position) {
            return position == getItemViewType();
        }

        @Override
        public int getItemViewType() {
            return INT_NEWEST_MUSIC_VIEW_TYPE;
        }

        @Override
        protected int getLayoutRes() {
            return R.layout.newest_music_view_layout;
        }

        @Override
        protected void onBindViewHolder(BaseRecycleViewViewHolder holder, int position) {

        }
    }

    private class RecommendMvViewType extends BaseRecycleViewViewType{

        public RecommendMvViewType(BaseRecycleViewAdapter adapter) {
            super(adapter);
        }

        @Override
        protected boolean isMatchViewType(int position) {
            return position == getItemViewType();
        }

        @Override
        public int getItemViewType() {
            return INT_RECOMMEND_MV_VIEW_TYPE;
        }

        @Override
        protected int getLayoutRes() {
            return R.layout.recommend_mv_layout;
        }

        @Override
        protected void onBindViewHolder(BaseRecycleViewViewHolder holder, int position) {

        }
    }

    private class RadioViewType extends BaseRecycleViewViewType{

        public RadioViewType(BaseRecycleViewAdapter adapter) {
            super(adapter);
        }

        @Override
        protected boolean isMatchViewType(int position) {
            return position == getItemViewType();
        }

        @Override
        public int getItemViewType() {
            return INT_RADIO_VIEW_TYPE;
        }

        @Override
        protected int getLayoutRes() {
            return R.layout.radio_layout;
        }

        @Override
        protected void onBindViewHolder(BaseRecycleViewViewHolder holder, int position) {

        }
    }

    private class AdjustOrderViewType extends BaseRecycleViewViewType{

        public AdjustOrderViewType(BaseRecycleViewAdapter adapter) {
            super(adapter);
        }

        @Override
        protected boolean isMatchViewType(int position) {
            return position == getItemViewType();
        }

        @Override
        public int getItemViewType() {
            return INT_ADJUST_ORDER_VIEW_TYPE;
        }

        @Override
        protected int getLayoutRes() {
            return R.layout.adjust_order_layout;
        }

        @Override
        protected void onBindViewHolder(BaseRecycleViewViewHolder holder, int position) {

        }
    }
}
