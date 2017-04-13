package com.example.recycleviewrelatedlibrary;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/4/12.
 */

public class BaseRecycleViewViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> viewSparseArray = new SparseArray<>();

    public BaseRecycleViewViewHolder(View itemView) {
        super(itemView);
        parseViews(this.itemView);
    }

    private void parseViews(View view) {
        if (view.getId() != View.NO_ID) {
            viewSparseArray.put(view.getId(), view);
        }
        if (view instanceof ViewGroup) {
            int childCount = ((ViewGroup) view).getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = ((ViewGroup) view).getChildAt(i);
                if(child != null){
                    parseViews(child);
                }
            }
        }
    }

    public <T extends View> T getView(int id){
        return (T)(viewSparseArray.get(id));
    }


}
