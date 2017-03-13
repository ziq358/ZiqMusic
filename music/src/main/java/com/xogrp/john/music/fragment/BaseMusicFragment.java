package com.xogrp.john.music.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xogrp.john.music.R;
import com.xogrp.john.music.listener.Drawer;
import com.xogrp.john.music.widget.ToolbarLeftMenuIcon;

/**
 * Created by Administrator on 2017/3/8.
 */

public abstract class BaseMusicFragment extends AbstractMusicFragment {

    protected Toolbar mToolbar;
    protected Drawer mDrawer;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof Drawer){
            mDrawer = (Drawer) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_base, container, false);
        mToolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        initToolbar(mToolbar);
        ViewGroup content = (ViewGroup) rootView.findViewById(R.id.fl_base_content);
        View child = onBaseCreateView(inflater, content, savedInstanceState);
        if(child != null){
             content.addView(child);
        }
        return rootView;
    }

    protected void initToolbar(Toolbar toolbar){
        toolbar.setTitle(getTitle());
        final ToolbarLeftMenuIcon navIcon = getToolbarLeftMenuIcon();
        toolbar.setNavigationIcon(navIcon.getMenuIcon());
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(navIcon.isHome()){
                    openDrawer();
                }else{
                    getActivity().onBackPressed();
                }
            }
        });
    }

    public void openDrawer(){
        mDrawer.openDrawer();
    }

    protected ToolbarLeftMenuIcon getToolbarLeftMenuIcon(){
        return ToolbarLeftMenuIcon.HOME;
    }

    protected String getTitle(){
        return null;
    }

    protected View onBaseCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        return null;
    }




}
