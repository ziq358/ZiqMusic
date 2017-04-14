package com.xogrp.john.music.adapter;

import android.content.Context;

import com.example.recycleviewrelatedlibrary.BaseRecycleViewAdapter;
import com.example.recycleviewrelatedlibrary.BaseRecycleViewViewHolder;
import com.example.recycleviewrelatedlibrary.BaseRecycleViewViewType;

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
        setData(mDataList);
    }

    @Override
    protected void initViewType(List<BaseRecycleViewViewType> viewTypesList) {
        viewTypesList.add(new AutoRollViewType());
        viewTypesList.add(new ThreeCircleViewType());
        viewTypesList.add(new RecommendSongListViewType());
        viewTypesList.add(new ExclusiveSendOutViewType());
        viewTypesList.add(new NewestMusicViewType());
        viewTypesList.add(new RecommendMvViewType());
        viewTypesList.add(new RadioViewType());
    }

    private class AutoRollViewType extends BaseRecycleViewViewType{

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

    private class ThreeCircleViewType extends BaseRecycleViewViewType{

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

    private class RecommendSongListViewType extends BaseRecycleViewViewType{

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

    private class ExclusiveSendOutViewType extends BaseRecycleViewViewType{

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

    private class NewestMusicViewType extends BaseRecycleViewViewType{

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

    private class RecommendMvViewType extends BaseRecycleViewViewType{

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

    private class RadioViewType extends BaseRecycleViewViewType{

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
