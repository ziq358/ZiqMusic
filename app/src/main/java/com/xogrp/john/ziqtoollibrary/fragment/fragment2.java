package com.xogrp.john.ziqtoollibrary.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xogrp.john.toollibrary.utils.ScreenshotManager;
import com.xogrp.john.ziqtoollibrary.R;

/**
 * Created by john on 24/02/2017.
 */

public class fragment2 extends android.support.v4.app.Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment2, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScreenshotManager.getInstance().takeScreenshot(getActivity());
            }
        });
    }
}
