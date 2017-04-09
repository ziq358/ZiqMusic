package com.xogrp.john.music.service;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by Administrator on 2017/3/22.
 */

public class MusicPlayService extends Service {


    private static final String TAG = "ziq";
    private IBinder mBinder = new MusicPlayBinder(this);
    private int mCurrentSongPosition = -1;
    private List<MusicInfo> mMusicInfoList;
    private MediaPlayer mMediaPlayer = new MediaPlayer();
    private boolean isPause = false;

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
        public void next() throws RemoteException {
            MusicPlayService service = musicPlayServiceWeakReference.get();
            if(service != null){
                service.next();
            }
        }

        @Override
        public void pause() throws RemoteException {
            MusicPlayService service = musicPlayServiceWeakReference.get();
            if(service != null){
                service.pause();
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
        public boolean isPlaying() throws RemoteException {
            MusicPlayService service = musicPlayServiceWeakReference.get();
            if(service != null){
                return service.isPlaying();
            }
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
        if(mMusicInfoList != null && mMusicInfoList.size() > 0 ){
            if(mCurrentSongPosition == -1){
                mCurrentSongPosition = 0;
            }
            if(isPause){
                mMediaPlayer.start();
                isPause = false;
            }else{
               playSong(mCurrentSongPosition);
            }
        }
    }

    public void next(){
        if(mMusicInfoList != null){
            int count = mMusicInfoList.size();
            if(count > 0 && mCurrentSongPosition == count - 1){
                mCurrentSongPosition = 0;
            }else{
                mCurrentSongPosition++;
            }
            playSong(mCurrentSongPosition);
        }

    }

    private void playSong(int position){
        if(mMediaPlayer != null){
            try {
                mMediaPlayer.reset();
//               通过ContentResolver 查出来的数据，是content：//  用Uri才能正确找到地址播放
//                Uri.parse(mMusicInfoList.get(position).data)
                mMediaPlayer.setDataSource(getApplicationContext(), Uri.parse(mMusicInfoList.get(position).data));
                mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                // 通过异步的方式装载媒体资源
                mMediaPlayer.prepareAsync();
                mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        mMediaPlayer.start();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void pause(){
        Log.e(TAG, "pause: ");
        if(mMediaPlayer != null){
            isPause = true;
            mMediaPlayer.pause();
        }
    }

    public void stop(){
        Log.e(TAG, "stop: ");
        if(mMediaPlayer != null){
            mMediaPlayer.stop();
        }
    }

    public boolean isPlaying(){
        return mMediaPlayer != null && mMediaPlayer.isPlaying();
    }

    public void setMusicInfoList(List<MusicInfo> musicInfoList) {
        this.mCurrentSongPosition = -1;
        this.mMusicInfoList = musicInfoList;
    }
}
