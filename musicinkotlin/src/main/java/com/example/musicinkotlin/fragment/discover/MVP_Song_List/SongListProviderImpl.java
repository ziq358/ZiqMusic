package com.example.musicinkotlin.fragment.discover.MVP_Song_List;

import android.content.Context;

import com.example.musicinkotlin.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/18.
 */

public class SongListProviderImpl implements SongListContract.SongListProvider {
    private Context mContext;

    public SongListProviderImpl(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public List<SongListItemModel> getSongList(int page) {
        List<SongListItemModel> mDataList = new ArrayList<>();
        switch (page){
            case 1:
                mDataList = initDataOne();
                break;
            case 2:
                mDataList = initDataTwo();
                break;
            case 3:
                mDataList = initDataThree();
                break;
            default:
                return mDataList;
        }
        mDataList.add(new SongListItemModel(R.drawable.icon_song_list_3, mContext.getString(R.string.s_song_list_load_more)));
        return mDataList;
    }

    public List<SongListItemModel> initDataOne(){
        List<SongListItemModel> mDataList = new ArrayList<>();
        mDataList.add(new SongListItemModel(R.drawable.song_list_1_1, mContext.getString(R.string.s_song_list_1_1)));
        mDataList.add(new SongListItemModel(R.drawable.song_list_1_2, mContext.getString(R.string.s_song_list_1_2)));
        mDataList.add(new SongListItemModel(R.drawable.song_list_1_3, mContext.getString(R.string.s_song_list_1_3)));
        mDataList.add(new SongListItemModel(R.drawable.song_list_1_4, mContext.getString(R.string.s_song_list_1_4)));
        mDataList.add(new SongListItemModel(R.drawable.song_list_1_5, mContext.getString(R.string.s_song_list_1_5)));
        mDataList.add(new SongListItemModel(R.drawable.song_list_1_6, mContext.getString(R.string.s_song_list_1_6)));
        mDataList.add(new SongListItemModel(R.drawable.song_list_1_7, mContext.getString(R.string.s_song_list_1_7)));
        mDataList.add(new SongListItemModel(R.drawable.song_list_1_8, mContext.getString(R.string.s_song_list_1_8)));
        mDataList.add(new SongListItemModel(R.drawable.song_list_1_9, mContext.getString(R.string.s_song_list_1_9)));
        mDataList.add(new SongListItemModel(R.drawable.song_list_1_10, mContext.getString(R.string.s_song_list_1_10)));
        mDataList.add(new SongListItemModel(R.drawable.song_list_1_11, mContext.getString(R.string.s_song_list_1_11)));
        return mDataList;
    }

    public List<SongListItemModel> initDataTwo(){
        List<SongListItemModel> mDataList = new ArrayList<>();
        mDataList.add(new SongListItemModel(R.drawable.song_list_1_12, mContext.getString(R.string.s_song_list_1_12)));
        mDataList.add(new SongListItemModel(R.drawable.song_list_1_13, mContext.getString(R.string.s_song_list_1_13)));
        mDataList.add(new SongListItemModel(R.drawable.song_list_1_14, mContext.getString(R.string.s_song_list_1_14)));
        mDataList.add(new SongListItemModel(R.drawable.song_list_1_15, mContext.getString(R.string.s_song_list_1_15)));
        mDataList.add(new SongListItemModel(R.drawable.song_list_1_16, mContext.getString(R.string.s_song_list_1_16)));
        mDataList.add(new SongListItemModel(R.drawable.song_list_1_17, mContext.getString(R.string.s_song_list_1_17)));
        mDataList.add(new SongListItemModel(R.drawable.song_list_1_18, mContext.getString(R.string.s_song_list_1_18)));
        mDataList.add(new SongListItemModel(R.drawable.song_list_1_19, mContext.getString(R.string.s_song_list_1_19)));
        mDataList.add(new SongListItemModel(R.drawable.song_list_1_20, mContext.getString(R.string.s_song_list_1_20)));
        mDataList.add(new SongListItemModel(R.drawable.song_list_1_21, mContext.getString(R.string.s_song_list_1_21)));
        return mDataList;
    }

    public List<SongListItemModel> initDataThree(){
        List<SongListItemModel> mDataList = new ArrayList<>();
        mDataList.add(new SongListItemModel(R.drawable.song_list_1_22, mContext.getString(R.string.s_song_list_1_22)));
        mDataList.add(new SongListItemModel(R.drawable.song_list_1_23, mContext.getString(R.string.s_song_list_1_23)));
        mDataList.add(new SongListItemModel(R.drawable.song_list_1_24, mContext.getString(R.string.s_song_list_1_24)));
        mDataList.add(new SongListItemModel(R.drawable.song_list_1_25, mContext.getString(R.string.s_song_list_1_25)));
        mDataList.add(new SongListItemModel(R.drawable.song_list_1_26, mContext.getString(R.string.s_song_list_1_26)));
        mDataList.add(new SongListItemModel(R.drawable.song_list_1_27, mContext.getString(R.string.s_song_list_1_27)));
        mDataList.add(new SongListItemModel(R.drawable.song_list_1_28, mContext.getString(R.string.s_song_list_1_28)));
        mDataList.add(new SongListItemModel(R.drawable.song_list_1_29, mContext.getString(R.string.s_song_list_1_29)));
        mDataList.add(new SongListItemModel(R.drawable.song_list_1_30, mContext.getString(R.string.s_song_list_1_30)));
        mDataList.add(new SongListItemModel(R.drawable.song_list_1_31, mContext.getString(R.string.s_song_list_1_31)));
        return mDataList;
    }


}
