package com.xogrp.john.music.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.xogrp.john.music.activity.LockActivity;
import com.xogrp.john.toollibrary.utils.TextUtil;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by Administrator on 2017/3/22.
 */

public class MusicPlayService extends Service {
    public static final String STOP_CMD = "com.xogrp.john.music.service.stop";
    public static final String NEXT_CMD = "com.xogrp.john.music.service.next";
    public static final String PLAY_CMD = "com.xogrp.john.music.service.play";
    public static final String PAUSE_CMD = "com.xogrp.john.music.service.pause";

    public static final String LOCK_SCREEN_CMD = "com.xogrp.john.music.service.lock";
    public static final String LOCK_SCREEN_CMD_FLAG = "com.xogrp.john.music.service.lock.flag";


    private static final String TAG = "ziq";
    private IBinder mBinder = new MusicPlayBinder(this);
    private int mCurrentSongPosition = -1;
    private List<MusicInfo> mMusicInfoList;
    private MediaPlayer mMediaPlayer = new MediaPlayer();
    private boolean isPause = false;
    private boolean isLockScreen = false;

    private MusicNotification mMusicNotification;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate: ");
        mMusicNotification = new MusicNotification(this);
        IntentFilter filter = new IntentFilter();
        filter.addAction(STOP_CMD);
        filter.addAction(NEXT_CMD);
        filter.addAction(PLAY_CMD);
        filter.addAction(PAUSE_CMD);
        filter.addAction(LOCK_SCREEN_CMD);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(mIntentReceiver, filter);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand: ");
        if (intent != null) {
            handleCommandIntent(intent);
        }
        return START_STICKY;
    }


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

    private final BroadcastReceiver mIntentReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(final Context context, final Intent intent) {
            handleCommandIntent(intent);
        }
    };

    public void handleCommandIntent(Intent intent){
        if(intent != null && !TextUtil.isTextEmptyOrNull(intent.getAction())){
            switch (intent.getAction()){
                case Intent.ACTION_SCREEN_OFF:
                    if(isPlaying() && !isLockScreen){
                        Intent lockActivity = new Intent(this, LockActivity.class);
                        lockActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(lockActivity);
                    }
                    break;
                case STOP_CMD:
                    mMusicNotification.cancelNotification();
                    stop();
                    break;
                case NEXT_CMD:
                    next();
                    break;
                case PLAY_CMD:
                    play();
                    break;
                case PAUSE_CMD:
                    pause();
                    break;
                case LOCK_SCREEN_CMD:
                    isLockScreen = intent.getBooleanExtra(LOCK_SCREEN_CMD_FLAG, true);
                    break;
            }
        }
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
        public MusicInfo getCurrentMusicInfo() throws RemoteException {
            MusicPlayService service = musicPlayServiceWeakReference.get();
            if(service != null){
                return service.getCurrentMusicInfo();
            }
            return null;
        }

        @Override
        public void setMusicList(List<MusicInfo> musicList) throws RemoteException {
            MusicPlayService service = musicPlayServiceWeakReference.get();
            if(service != null){
                service.setMusicInfoList(musicList);
            }
        }
    }

    public MusicInfo getCurrentMusicInfo(){
        if(mMusicInfoList != null && mMusicInfoList.size() > 0 ){
            if(mCurrentSongPosition == -1){
                mCurrentSongPosition = 0;
            }
            return mMusicInfoList.get(mCurrentSongPosition);
        }
        return null;
    }

    public void play(){
        Log.e(TAG, "play: ");
        if(mMusicInfoList != null && mMusicInfoList.size() > 0 && !mMediaPlayer.isPlaying()){
            if(mCurrentSongPosition == -1){
                mCurrentSongPosition = 0;
            }
            if(isPause){
                mMediaPlayer.start();
                isPause = false;
            }else{
               playSong(mCurrentSongPosition);
            }
            mMusicNotification.updateNotification();
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
        mMusicNotification.updateNotification();
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
                        mMusicNotification.updateNotification();
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
        mMusicNotification.updateNotification();
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
        if(mCurrentSongPosition == -1){
            mCurrentSongPosition = 0;
        }
    }


}
