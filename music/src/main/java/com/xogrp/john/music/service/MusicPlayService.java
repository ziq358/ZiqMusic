package com.xogrp.john.music.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by Administrator on 2017/3/22.
 */

public class MusicPlayService extends Service {


    private static final String TAG = "ziq";
    private IBinder mBinder = new MusicPlayBinder(this);
    private List<MusicInfo> mMusicInfoList;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind: ");
        return mBinder;
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy: ");
        super.onDestroy();
    }

    private static final class MusicPlayBinder extends MusicPlayServiceInterface.Stub{

        private WeakReference<MusicPlayService> musicPlayServiceWeakReference;

        public MusicPlayBinder(MusicPlayService service) {
            musicPlayServiceWeakReference = new WeakReference<MusicPlayService>(service);
        }

        @Override
        public void play() throws RemoteException {
            MusicPlayService service = musicPlayServiceWeakReference.get();
            if(service != null){
                service.play();
            }
        }

        @Override
        public void stop() throws RemoteException {
            MusicPlayService service = musicPlayServiceWeakReference.get();
            if(service != null){
                service.stop();
            }
        }

        @Override
        public void next() throws RemoteException {

        }

        @Override
        public boolean isPlaying() throws RemoteException {
            return false;
        }

        @Override
        public void setMusicList(List<MusicInfo> musicList) throws RemoteException {
            MusicPlayService service = musicPlayServiceWeakReference.get();
            if(service != null){
                service.setMusicInfoList(musicList);
            }
        }
    }

    public void play(){
        Log.e(TAG, "play: ");
    }

    public void stop(){
        Log.e(TAG, "stop: ");
    }

    public void setMusicInfoList(List<MusicInfo> musicInfoList) {
        this.mMusicInfoList = musicInfoList;
    }
}
