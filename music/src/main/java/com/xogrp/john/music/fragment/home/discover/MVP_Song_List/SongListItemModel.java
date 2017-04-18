package com.xogrp.john.music.fragment.home.discover.MVP_Song_List;

/**
 * Created by john on 18/04/2017.
 */

public class SongListItemModel {
    public int songImageId;
    public String songContent;

    public SongListItemModel(int songImageId, String songContent) {
        this.songImageId = songImageId;
        this.songContent = songContent;
    }
}
