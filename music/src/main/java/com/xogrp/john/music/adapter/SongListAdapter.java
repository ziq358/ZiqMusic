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

public class SongListAdapter extends BaseRecycleViewAdapter<Integer> {

    private final static int INT_ONE_ITEM_VIEW_TYPE = 0;
    private final static int INT_TWO_ITEM_VIEW_TYPE = 1;
    private final static int INT_LOAD_MORE_VIEW_TYPE = 2;

    private List<Integer> mIntDataList = new ArrayList<>();
    private List<String> mStringDataList = new ArrayList<>();

    public SongListAdapter(Context context) {
        super(context);
        initDataOne();
        setData(mIntDataList);
    }

    @Override
    protected void initViewType(List<BaseRecycleViewViewType> viewTypesList) {
        viewTypesList.add(new OneItemViewType(this));
        viewTypesList.add(new TwoItemViewType(this));
        viewTypesList.add(new LoadMoreViewType(this));
    }

    private class OneItemViewType extends BaseRecycleViewViewType{

        public OneItemViewType(BaseRecycleViewAdapter adapter) {
            super(adapter);
        }

        @Override
        protected boolean isMatchViewType(int position) {
            return position == 0;
        }

        @Override
        public int getItemViewType() {
            return INT_ONE_ITEM_VIEW_TYPE;
        }

        @Override
        protected int getLayoutRes() {
            return R.layout.song_list_one_item_layout;
        }

        @Override
        protected void onBindViewHolder(BaseRecycleViewViewHolder holder, int position) {

        }
    }

    private class TwoItemViewType extends BaseRecycleViewViewType{

        public TwoItemViewType(BaseRecycleViewAdapter adapter) {
            super(adapter);
        }

        @Override
        protected boolean isMatchViewType(int position) {
            BaseRecycleViewAdapter adapter = getAdapter();
            return position != 0 &&  adapter != null && position != adapter.getItemCount() - 1 ;
        }

        @Override
        public int getItemViewType() {
            return INT_TWO_ITEM_VIEW_TYPE;
        }

        @Override
        protected int getLayoutRes() {
            return R.layout.song_list_two_item_layout;
        }

        @Override
        protected void onBindViewHolder(BaseRecycleViewViewHolder holder, int position) {

        }
    }

    private class LoadMoreViewType extends BaseRecycleViewViewType{

        public LoadMoreViewType(BaseRecycleViewAdapter adapter) {
            super(adapter);
        }

        @Override
        protected boolean isMatchViewType(int position) {
            BaseRecycleViewAdapter adapter = getAdapter();
            return adapter != null && position == adapter.getItemCount() - 1 ;
        }

        @Override
        public int getItemViewType() {
            return INT_LOAD_MORE_VIEW_TYPE;
        }

        @Override
        protected int getLayoutRes() {
            return R.layout.song_list_load_more_layout;
        }

        @Override
        protected void onBindViewHolder(BaseRecycleViewViewHolder holder, int position) {

        }
    }

    public void initDataOne(){
        mIntDataList.add(R.drawable.song_list_1_1);
        mIntDataList.add(R.drawable.song_list_1_2);
        mIntDataList.add(R.drawable.song_list_1_3);
        mIntDataList.add(R.drawable.song_list_1_4);
        mIntDataList.add(R.drawable.song_list_1_5);
        mIntDataList.add(R.drawable.song_list_1_6);
        mIntDataList.add(R.drawable.song_list_1_7);
        mIntDataList.add(R.drawable.song_list_1_8);
        mIntDataList.add(R.drawable.song_list_1_9);
        mIntDataList.add(R.drawable.song_list_1_10);


        mStringDataList.add(getContext().getString(R.string.s_song_list_1_1));
        mStringDataList.add(getContext().getString(R.string.s_song_list_1_2));
        mStringDataList.add(getContext().getString(R.string.s_song_list_1_3));
        mStringDataList.add(getContext().getString(R.string.s_song_list_1_4));
        mStringDataList.add(getContext().getString(R.string.s_song_list_1_5));
        mStringDataList.add(getContext().getString(R.string.s_song_list_1_6));
        mStringDataList.add(getContext().getString(R.string.s_song_list_1_7));
        mStringDataList.add(getContext().getString(R.string.s_song_list_1_8));
        mStringDataList.add(getContext().getString(R.string.s_song_list_1_9));
        mStringDataList.add(getContext().getString(R.string.s_song_list_1_10));
    }

    public void initDataTwo(){
        mIntDataList.add(R.drawable.song_list_1_11);
        mIntDataList.add(R.drawable.song_list_1_12);
        mIntDataList.add(R.drawable.song_list_1_13);
        mIntDataList.add(R.drawable.song_list_1_14);
        mIntDataList.add(R.drawable.song_list_1_15);
        mIntDataList.add(R.drawable.song_list_1_16);
        mIntDataList.add(R.drawable.song_list_1_17);
        mIntDataList.add(R.drawable.song_list_1_18);
        mIntDataList.add(R.drawable.song_list_1_19);
        mIntDataList.add(R.drawable.song_list_1_20);

        mStringDataList.add(getContext().getString(R.string.s_song_list_1_11));
        mStringDataList.add(getContext().getString(R.string.s_song_list_1_12));
        mStringDataList.add(getContext().getString(R.string.s_song_list_1_13));
        mStringDataList.add(getContext().getString(R.string.s_song_list_1_14));
        mStringDataList.add(getContext().getString(R.string.s_song_list_1_15));
        mStringDataList.add(getContext().getString(R.string.s_song_list_1_16));
        mStringDataList.add(getContext().getString(R.string.s_song_list_1_17));
        mStringDataList.add(getContext().getString(R.string.s_song_list_1_18));
        mStringDataList.add(getContext().getString(R.string.s_song_list_1_19));
        mStringDataList.add(getContext().getString(R.string.s_song_list_1_20));
    }

    public void initDataThree(){
        mIntDataList.add(R.drawable.song_list_1_21);
        mIntDataList.add(R.drawable.song_list_1_22);
        mIntDataList.add(R.drawable.song_list_1_23);
        mIntDataList.add(R.drawable.song_list_1_24);
        mIntDataList.add(R.drawable.song_list_1_25);
        mIntDataList.add(R.drawable.song_list_1_26);
        mIntDataList.add(R.drawable.song_list_1_27);
        mIntDataList.add(R.drawable.song_list_1_28);
        mIntDataList.add(R.drawable.song_list_1_29);
        mIntDataList.add(R.drawable.song_list_1_30);
        mIntDataList.add(R.drawable.song_list_1_31);

        mStringDataList.add(getContext().getString(R.string.s_song_list_1_21));
        mStringDataList.add(getContext().getString(R.string.s_song_list_1_22));
        mStringDataList.add(getContext().getString(R.string.s_song_list_1_23));
        mStringDataList.add(getContext().getString(R.string.s_song_list_1_24));
        mStringDataList.add(getContext().getString(R.string.s_song_list_1_25));
        mStringDataList.add(getContext().getString(R.string.s_song_list_1_26));
        mStringDataList.add(getContext().getString(R.string.s_song_list_1_27));
        mStringDataList.add(getContext().getString(R.string.s_song_list_1_28));
        mStringDataList.add(getContext().getString(R.string.s_song_list_1_29));
        mStringDataList.add(getContext().getString(R.string.s_song_list_1_30));
        mStringDataList.add(getContext().getString(R.string.s_song_list_1_31));
    }

    private void addLoadMoreItem(){
        mIntDataList.add(R.drawable.icon_song_list_3);

        mStringDataList.add(getContext().getString(R.string.s_song_list_load_more));
    }
}
