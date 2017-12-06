package com.example.musicinkotlin.fragment.discover.MVP_Song_List;

import android.os.Looper;

import java.util.List;

/**
 * Created by Administrator on 2017/4/18.
 */

public class SongListPresenterImpl implements SongListContract.SongListPresenter {
    private SongListContract.SongListProvider provider;
    private SongListContract.SongListViewRenderer viewRenderer;

    private int page = 1;
    private boolean isLoadMoreEnable = true;

    public SongListPresenterImpl(SongListContract.SongListProvider provider,
                                 SongListContract.SongListViewRenderer viewRenderer) {
        this.provider = provider;
        this.viewRenderer = viewRenderer;
    }

    @Override
    public void init() {
        viewRenderer.showSongList(provider.getSongList(1));
    }

    @Override
    public synchronized void loadMore() {
        android.os.Handler handler = new android.os.Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                page++;
                if(isLoadMoreEnable){
                    List<SongListItemModel> list = provider.getSongList(page);
                    if(list != null && list.size() > 0){
                        viewRenderer.showSongList(list);
                    }else{
                        isLoadMoreEnable = false;
                        viewRenderer.unableLoadMore();
                    }
                }
            }
        }, 5000);
    }
}
