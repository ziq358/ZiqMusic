package com.example.recycleviewrelatedlibrary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/4/12.
 */

public abstract class BaseViewType {

    protected abstract boolean isMatchViewType(int position);

    public abstract int getItemViewType();

    protected abstract int getLayoutRes();

    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutRes(), parent, false);
        return new BaseViewHolder(view);
    }

    protected abstract void onBindViewHolder(BaseViewHolder holder, int position);
}
