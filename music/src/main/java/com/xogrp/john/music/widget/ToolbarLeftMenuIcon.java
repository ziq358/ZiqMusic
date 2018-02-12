package com.xogrp.john.music.widget;

import com.xogrp.john.music.R;

/**
 * Created by Administrator on 2017/3/9.
 */

public abstract class ToolbarLeftMenuIcon {
    public abstract int getMenuIcon();
    public abstract boolean isHome();

    public final static ToolbarLeftMenuIcon HOME = new HomeIcon();


    private static class HomeIcon extends ToolbarLeftMenuIcon{

        @Override
        public int getMenuIcon() {
            return R.drawable.ic_hamberger;
        }

        @Override
        public boolean isHome() {
            return true;
        }
    }

}
