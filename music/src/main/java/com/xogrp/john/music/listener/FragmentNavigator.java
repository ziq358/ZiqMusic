package com.xogrp.john.music.listener;

import com.xogrp.john.music.fragment.AbstractMusicFragment;

/**
 * Created by john on 03/03/2017.
 */

public interface FragmentNavigator {
    int getContainer();
    void addFragment(AbstractMusicFragment fragment);
}
