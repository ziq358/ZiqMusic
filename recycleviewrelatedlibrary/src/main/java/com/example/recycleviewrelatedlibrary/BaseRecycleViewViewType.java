package com.example.recycleviewrelatedlibrary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2017/4/12.
 */

public abstract class BaseRecycleViewViewType {
    private WeakReference<BaseRecycleViewAdapter> adapterWeakReference;

    public BaseRecycleViewViewType(BaseRecycleViewAdapter adapter) {
        adapterWeakReference = new WeakReference<BaseRecycleViewAdapter>(adapter);
    }

    public BaseRecycleViewAdapter getAdapter() {
        return adapterWeakReference.get();
    }

    protected abstract boolean isMatchViewType(int position);

    public abstract int getItemViewType();

    protected abstract int getLayoutRes();

    public BaseRecycleViewViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutRes(), parent, false);
        return new BaseRecycleViewViewHolder(view);
    }

    protected abstract void onBindViewHolder(BaseRecycleViewViewHolder holder, int position);
}
