package com.xogrp.john.music.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.xogrp.john.music.R;
import com.xogrp.john.music.service.MusicPlayService;
import com.xogrp.john.music.service.MusicPlayServiceInterface;
import com.xogrp.john.music.utils.MusicUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2017/5/1 0001.
 */

public class LockActivity extends AbstractMusicActivity implements View.OnClickListener{

    private TextView mTime,mDate,mMusicName,mMusicArtsit;
    private ImageView pre,play,next,fav;
    private Handler mHandlerTime;

    private MusicPlayServiceInterface mServiceBinder;
    private boolean mBound;

    @Override
    public int getContainer() {
        return R.id.content;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent();
        intent.setAction(MusicPlayService.LOCK_SCREEN_CMD);
        intent.putExtra(MusicPlayService.LOCK_SCREEN_CMD_FLAG,true);
        sendBroadcast(intent);

        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        //覆盖在锁屏界面上
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        setContentView(R.layout.activity_lock);
        mTime = (TextView) findViewById(R.id.lock_time);
        mDate = (TextView) findViewById(R.id.lock_date);
        mMusicName = (TextView) findViewById(R.id.lock_music_name);
        mMusicArtsit = (TextView) findViewById(R.id.lock_music_artsit);
        pre = (ImageView) findViewById(R.id.lock_music_pre);
        play = (ImageView) findViewById(R.id.lock_music_play);
        next = (ImageView) findViewById(R.id.lock_music_next);
        fav = (ImageView) findViewById(R.id.lock_music_fav);
        mHandlerTime = new Handler(Looper.getMainLooper());
        mHandlerTime.post(updateTimeRunnable);
        pre.setOnClickListener(this);
        play.setOnClickListener(this);
        next.setOnClickListener(this);
        fav.setOnClickListener(this);
        Intent intentService = new Intent(this, MusicPlayService.class);
        bindService(intentService, serviceConnection, 0);
    }

    Runnable updateTimeRunnable = new Runnable() {
        @Override
        public void run() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm-MM月dd日 E", Locale.CHINESE);
            String date[] = simpleDateFormat.format(new Date()).split("-");
            mTime.setText(date[0]);
            mDate.setText(date[1]);
            mHandlerTime.postDelayed(updateTimeRunnable,300);
        }
    };

    @Override
    protected void onDestroy() {
        Log.e("ziq", "LockActivity onDestroy: ");
        Intent intent = new Intent();
        intent.setAction(MusicPlayService.LOCK_SCREEN_CMD);
        intent.putExtra(MusicPlayService.LOCK_SCREEN_CMD_FLAG,false);
        sendBroadcast(intent);
        mHandlerTime.removeCallbacks(updateTimeRunnable);
        mServiceBinder = null;
        if(mBound){
            unbindService(serviceConnection);
            mBound = false;
        }
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lock_music_pre:
                break;
            case R.id.lock_music_play:
                try {
                    if (mServiceBinder.isPlaying()) {
                        mServiceBinder.pause();
                        play.setImageResource(R.drawable.lock_btn_play);
                    } else {
                        mServiceBinder.play();
                        play.setImageResource(R.drawable.lock_btn_pause);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.lock_music_next:
                try {
                    mServiceBinder.next();
                    play.setImageResource(R.drawable.lock_btn_pause);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.lock_music_fav:
                break;
        }
    }



    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mServiceBinder = MusicPlayServiceInterface.Stub.asInterface(iBinder);
            try {
                if (mServiceBinder.isPlaying()) {
                    play.setImageResource(R.drawable.lock_btn_pause);
                } else {
                    play.setImageResource(R.drawable.lock_btn_play);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBound = false;
        }
    };
}
