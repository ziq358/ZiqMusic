package com.xogrp.john.music.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.recycleviewrelatedlibrary.BaseRecycleViewAdapter;
import com.example.recycleviewrelatedlibrary.BaseRecycleViewViewHolder;
import com.example.recycleviewrelatedlibrary.BaseRecycleViewViewType;
import com.xogrp.john.music.R;
import com.xogrp.john.music.fragment.home.discover.MVP_Song_List.SongListItemModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by john on 14/04/2017.
 */

public class SongListAdapter extends BaseRecycleViewAdapter<SongListItemModel> {

    private final static int INT_ONE_ITEM_VIEW_TYPE = 0;
    private final static int INT_TWO_ITEM_VIEW_TYPE = 1;
    private final static int INT_LOAD_MORE_VIEW_TYPE = 2;

    private List<SongListItemModel> mDataList = new ArrayList<>();

    public SongListAdapter(Context context) {
        super(context);
        setData(mDataList);
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
            TextView textView = holder.getView(R.id.content);
            SongListItemModel model = (SongListItemModel) getAdapter().getItem(position);
            textView.setText(model.songContent);
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
            TextView textView = holder.getView(R.id.content);
            SongListItemModel model = (SongListItemModel) getAdapter().getItem(position);
            textView.setText(model.songContent);
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
            TextView textView = holder.getView(R.id.content);
            SongListItemModel model = (SongListItemModel) getAdapter().getItem(position);
            textView.setText(model.songContent);
        }
    }

}
