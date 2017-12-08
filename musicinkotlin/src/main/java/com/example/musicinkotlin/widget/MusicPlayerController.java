package com.example.musicinkotlin.widget;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.musicinkotlin.MusicPlayServiceBinder;
import com.example.musicinkotlin.R;
import com.example.musicinkotlin.service.musicService.MusicInfo;
import com.example.musicinkotlin.service.musicService.MusicPlayService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by john on 16/03/2017.
 */

public class MusicPlayerController implements View.OnClickListener{
    private static final int READ_EXTERNAL_STORAGE_REQUEST_ID = 1;
    private Activity mContext;
    private LayoutInflater mLayoutInflater;
    private View mRootView;
    private ProgressBar mProgressBar;
    private TextView mTvMusicName;

    private MusicPlayServiceBinder mServiceBinder;

    private ImageView mPlayOrStopBtn;

    public MusicPlayerController(ViewGroup parentView, Activity activity) {
        this.mContext = activity;
        this.mLayoutInflater = LayoutInflater.from(mContext);
        this.mRootView = mLayoutInflater.inflate(R.layout.music_player_bottom_layout, parentView, false);
        parentView.addView(mRootView);
        startMusicPlayService(mContext);
        bindMusicPlauService(mContext);
        initView();
    }



    private void initView() {
        mPlayOrStopBtn = (ImageView) mRootView.findViewById(R.id.img_music_play_controller);
        mPlayOrStopBtn.setOnClickListener(this);
        mTvMusicName = (TextView) mRootView.findViewById(R.id.tv_music_name);
        mRootView.findViewById(R.id.img_music_next).setOnClickListener(this);
        mProgressBar = mRootView.findViewById(R.id.music_progress);
        mProgressBar.setMax(100);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MusicPlayService.UPDATE_STATUS_CMD);
        mContext.registerReceiver(mBroadcastReceiver, intentFilter);
    }

    public View getView(){
        return mRootView;
    }

    private void startMusicPlayService(Context context){
        Intent intent = new Intent(context, MusicPlayService.class);
        context.startService(intent);
    }

    private void bindMusicPlauService(Context context){
        Intent intent = new Intent(context, MusicPlayService.class);
        context.bindService(intent, serviceConnection, 0);
    }

    public void stopMusicPlayService(Context context){
        Intent intent = new Intent(context, MusicPlayService.class);
        context.stopService(intent);
    }

    public void unbindMusicPlayService(){
        mServiceBinder = null;
        mContext.unbindService(serviceConnection);
        mContext.unregisterReceiver(mBroadcastReceiver);
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mServiceBinder = MusicPlayServiceBinder.Stub.asInterface(iBinder);
            List<MusicInfo> songList = new ArrayList();
            songList.add(new MusicInfo("我们不一样", Uri.parse("android.resource://" + mContext.getPackageName() + "/" + R.raw.wo_men_bu_yi_yang)));
            songList.add(new MusicInfo("刚好遇见你", Uri.parse("android.resource://" + mContext.getPackageName() + "/" + R.raw.gang_hao_yu_jian_ni)));
            songList.add(new MusicInfo("带你去旅行", Uri.parse("android.resource://" + mContext.getPackageName() + "/" + R.raw.dai_ni_qu_lu_xing)));
            songList.add(new MusicInfo("演员", Uri.parse("android.resource://" + mContext.getPackageName() + "/" + R.raw.yan_yuan)));
            try {
                mServiceBinder.setMusicList(songList);
                updateMusicStatus();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mServiceBinder = null;
        }
    };

    @Override
    public void onClick(View view) {
        if(mServiceBinder != null){
            try {
                switch (view.getId()){
                    case R.id.img_music_play_controller:
                        if(mServiceBinder.isPlaying()){
                            mServiceBinder.pause();
                        }else{
                            mServiceBinder.play();
                        }
                        break;
                    case R.id.img_music_next:
                        mServiceBinder.next();
                        break;
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateMusicStatus(){
        MusicInfo currentMusicInfo = null;
        try {
            currentMusicInfo = mServiceBinder.getCurrentMusicInfo();
            mTvMusicName.setText(currentMusicInfo.getName());
            mPlayOrStopBtn.setImageDrawable( ContextCompat.getDrawable(mPlayOrStopBtn.getContext(), mServiceBinder.isPlaying() ?  R.mipmap.music_bottom_player_pause : R.mipmap.music_bottom_player_play));
            mProgressBar.setProgress(mServiceBinder.getCurrentPosition());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent != null && !TextUtils.isEmpty(intent.getAction())){
                switch (intent.getAction()){
                    case MusicPlayService.UPDATE_STATUS_CMD:
                        updateMusicStatus();
                        break;
                }
            }
        }
    };

}
