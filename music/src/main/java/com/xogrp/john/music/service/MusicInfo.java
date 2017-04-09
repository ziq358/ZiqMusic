package com.xogrp.john.music.service;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/4/9.
 */

public class MusicInfo implements Parcelable {

    public static final String KEY_SONG_ID = "songId";
    public static final String KEY_ALBUM_ID = "albumId";
    public static final String KEY_ALBUM_NAME = "albumName";
    public static final String KEY_ALBUM_DATA = "albumData";
    public static final String KEY_DURATION = "duration";
    public static final String KEY_MUSIC_NAME = "musicName";
    public static final String KEY_ARTIST = "artist";
    public static final String KEY_ARTIST_ID = "artist_id";
    public static final String KEY_DATA = "data";
    public static final String KEY_FOLDER = "folder";
    public static final String KEY_SIZE = "size";
    public static final String KEY_FAVORITE = "favorite";
    public static final String KEY_LRC = "lrc";
    public static final String KEY_IS_LOCAL = "isLocal";
    public static final String KEY_SORT = "sort";


    public long songId = -1;
    public int albumId = -1;
    public String albumName;
    public String albumData;
    public int duration;
    public String musicName;
    public String artist;
    public long artistId;
    public String data;
    public String folder;
    public String lrc;
    public boolean isLocal;
    public String sort;

    public int size;
    public int favorite = 0;
    public int getFavorite() {
        return favorite;
    }
    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }


    public MusicInfo() {
    }

    protected MusicInfo(Parcel in) {
        Bundle bundle = in.readBundle();
        this.songId = bundle.getLong(KEY_SONG_ID);
        this.albumId = bundle.getInt(KEY_ALBUM_ID);
        this.albumName = bundle.getString(KEY_ALBUM_NAME);
        this.duration = bundle.getInt(KEY_DURATION);
        this.musicName = bundle.getString(KEY_MUSIC_NAME);
        this.artist = bundle.getString(KEY_ARTIST);
        this.artistId = bundle.getLong(KEY_ARTIST_ID);
        this.data = bundle.getString(KEY_DATA);
        this.folder = bundle.getString(KEY_FOLDER);
        this.albumData = bundle.getString(KEY_ALBUM_DATA);
        this.size = bundle.getInt(KEY_SIZE);
        this.lrc = bundle.getString(KEY_LRC);
        this.isLocal = bundle.getBoolean(KEY_IS_LOCAL);
        this.sort = bundle.getString(KEY_SORT);
    }

    public static final Creator<MusicInfo> CREATOR = new Creator<MusicInfo>() {
        @Override
        public MusicInfo createFromParcel(Parcel in) {
            return new MusicInfo(in);
        }

        @Override
        public MusicInfo[] newArray(int size) {
            return new MusicInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        Bundle bundle = new Bundle();
        bundle.putLong(KEY_SONG_ID, songId);
        bundle.putInt(KEY_ALBUM_ID, albumId);
        bundle.putString(KEY_ALBUM_NAME, albumName);
        bundle.putString(KEY_ALBUM_DATA, albumData);
        bundle.putInt(KEY_DURATION, duration);
        bundle.putString(KEY_MUSIC_NAME, musicName);
        bundle.putString(KEY_ARTIST, artist);
        bundle.putLong(KEY_ARTIST_ID, artistId);
        bundle.putString(KEY_DATA, data);
        bundle.putString(KEY_FOLDER, folder);
        bundle.putInt(KEY_SIZE, size);
        bundle.putString(KEY_LRC, lrc);
        bundle.putBoolean(KEY_IS_LOCAL, isLocal);
        bundle.putString(KEY_SORT, sort);
        parcel.writeBundle(bundle);
    }
}
