package com.xogrp.john.music.fragment.home.discover;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xogrp.john.music.R;
import com.xogrp.john.music.adapter.SongListAdapter;
import com.xogrp.john.music.fragment.BaseMusicFragment;
import com.xogrp.john.music.fragment.home.discover.MVP_Song_List.SongListContract;
import com.xogrp.john.music.fragment.home.discover.MVP_Song_List.SongListItemModel;
import com.xogrp.john.music.fragment.home.discover.MVP_Song_List.SongListPresenterImpl;
import com.xogrp.john.music.fragment.home.discover.MVP_Song_List.SongListProviderImpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by john on 16/03/2017.
 */

public class SongListFragment extends BaseMusicFragment implements SongListContract.SongListViewRenderer{

    public static final String FRAGMENT_TAG = "song_list_fragment";

    @BindView(R.id.rv_song_list)
    RecyclerView mRecyclerView;

    private SongListPresenterImpl presenter;
    private SongListAdapter songListAdapter;

    @Override
    public String getFragmentTag() {
        return FRAGMENT_TAG;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        presenter = new SongListPresenterImpl(new SongListProviderImpl(getContext()), this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_song_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        songListAdapter = new SongListAdapter(getContext());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(songListAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if(linearLayoutManager.findLastCompletelyVisibleItemPosition() == recyclerView.getAdapter().getItemCount() - 1){
                    if(presenter != null) presenter.loadMore();
                }
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.init();
    }

    @Override
    public void showSongList(List<SongListItemModel> songList) {
        songListAdapter.removeDataList(songListAdapter.getItemCount() - 1);
        songListAdapter.addDataList(songList);
    }

    @Override
    public void unableLoadMore() {
//        songListAdapter.removeDataList(songListAdapter.getItemCount() - 1);
//        songListAdapter.notifyDataSetChanged();
    }
}
