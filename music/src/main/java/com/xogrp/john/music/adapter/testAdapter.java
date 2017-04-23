package com.xogrp.john.music.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.recycleviewrelatedlibrary.BaseRecycleViewAdapter;
import com.example.recycleviewrelatedlibrary.BaseRecycleViewViewHolder;
import com.example.recycleviewrelatedlibrary.BaseRecycleViewViewType;
import com.xogrp.john.music.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/22.
 */

public class testAdapter extends BaseRecycleViewAdapter<String> {

    public testAdapter(Context context) {
        super(context);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(""+i);
        }
        setData(list);
    }

    @Override
    protected void initViewType(List<BaseRecycleViewViewType> viewTypesList) {
        viewTypesList.add(new RadioViewType(this));
    }

    private class RadioViewType extends BaseRecycleViewViewType{

        public RadioViewType(BaseRecycleViewAdapter adapter) {
            super(adapter);
        }

        @Override
        protected boolean isMatchViewType(int position) {
            return true;
        }

        @Override
        public int getItemViewType() {
            return 0;
        }

        @Override
        protected int getLayoutRes() {
            return R.layout.test_layout;
        }

        @Override
        protected void onBindViewHolder(BaseRecycleViewViewHolder holder, int position) {
            TextView textView = holder.getView(R.id.content);
            textView.setText((String)(getAdapter().getItem(position)));
        }
    }


}
