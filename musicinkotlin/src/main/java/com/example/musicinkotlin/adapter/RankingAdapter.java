package com.example.musicinkotlin.adapter;

import android.content.Context;

import com.example.musicinkotlin.R;
import com.example.recycleviewrelatedlibrary.BaseRecycleViewAdapter;
import com.example.recycleviewrelatedlibrary.BaseRecycleViewViewHolder;
import com.example.recycleviewrelatedlibrary.BaseRecycleViewViewType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by john on 14/04/2017.
 */

public class RankingAdapter extends BaseRecycleViewAdapter<String> {

    public final static int INT_ONE_ITEM_VIEW_TYPE = 0;
    private final static int INT_TWO_ITEM_VIEW_TYPE = 1;
    public final static int INT_LOAD_MORE_VIEW_TYPE = 2;

    private List<String> mDataList = new ArrayList<>();
    public RankingAdapter(Context context) {
        super(context);
        mDataList.add("1");
        mDataList.add("2");
        mDataList.add("3");
        setData(mDataList);
    }

    @Override
    protected void initViewType(List<BaseRecycleViewViewType> viewTypesList) {
        viewTypesList.add(new OneViewType(this));
        viewTypesList.add(new TwoViewType(this));
        viewTypesList.add(new ThreeViewType(this));
    }


    private class OneViewType extends BaseRecycleViewViewType{

        public OneViewType(BaseRecycleViewAdapter adapter) {
            super(adapter);
        }

        @Override
        protected boolean isMatchViewType(int position) {
            return position == getItemViewType();
        }

        @Override
        public int getItemViewType() {
            return INT_ONE_ITEM_VIEW_TYPE;
        }

        @Override
        protected int getLayoutRes() {
            return R.layout.ranking_one_item_layout;
        }

        @Override
        protected void onBindViewHolder(BaseRecycleViewViewHolder holder, int position) {
        }
    }

    private class TwoViewType extends BaseRecycleViewViewType{

        public TwoViewType(BaseRecycleViewAdapter adapter) {
            super(adapter);
        }

        @Override
        protected boolean isMatchViewType(int position) {
            return position == getItemViewType();
        }

        @Override
        public int getItemViewType() {
            return INT_TWO_ITEM_VIEW_TYPE;
        }

        @Override
        protected int getLayoutRes() {
            return R.layout.ranking_two_item_layout;
        }

        @Override
        protected void onBindViewHolder(BaseRecycleViewViewHolder holder, int position) {
        }
    }

    private class ThreeViewType extends BaseRecycleViewViewType{

        public ThreeViewType(BaseRecycleViewAdapter adapter) {
            super(adapter);
        }

        @Override
        protected boolean isMatchViewType(int position) {
            return position == getItemViewType();
        }

        @Override
        public int getItemViewType() {
            return INT_LOAD_MORE_VIEW_TYPE;
        }

        @Override
        protected int getLayoutRes() {
            return R.layout.ranking_three_item_layout;
        }

        @Override
        protected void onBindViewHolder(BaseRecycleViewViewHolder holder, int position) {
        }
    }

}
