package com.xogrp.john.music.fragment.home.discover.MVP_Song_List;

import java.util.List;

/**
 * Created by john on 18/04/2017.
 */

public class SongListContract {

    public interface SongListProvider{
        List<SongListItemModel> getSongList(int page);
    }

    public interface SongListViewRenderer{
        void showSongList(List<SongListItemModel> songList);
        void unableLoadMore();
    }

    public interface SongListPresenter{
        void init();
        void loadMore();
    }
}
