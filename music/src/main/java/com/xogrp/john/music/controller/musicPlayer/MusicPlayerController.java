package com.xogrp.john.music.controller.musicPlayer;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.xogrp.john.music.R;
import com.xogrp.john.music.service.MusicPlayService;
import com.xogrp.john.music.service.MusicPlayServiceInterface;
import com.xogrp.john.music.utils.MusicUtil;

/**
 * Created by john on 16/03/2017.
 */

public class MusicPlayerController implements View.OnClickListener{
    private static final int READ_EXTERNAL_STORAGE_REQUEST_ID = 1;
    private Activity mContext;
    private LayoutInflater mLayoutInflater;
    private View mRootView;

    private MusicPlayServiceInterface mServiceBinder;
    private boolean mBound;

    private ImageView mPlayOrStopBtn;

    public MusicPlayerController(ViewGroup parentView,Activity activity) {
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

        mRootView.findViewById(R.id.img_music_list).setOnClickListener(this);
        mRootView.findViewById(R.id.img_music_next).setOnClickListener(this);
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

    private void stopMusicPlayService(Context context){
        Intent intent = new Intent(context, MusicPlayService.class);
        context.stopService(intent);
    }

    private void unbindMusicPlayService(Context context){
        if(mBound){
            context.unbindService(serviceConnection);
            mBound = false;
        }
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.e("ziq", "bindService onServiceConnected: ");
            mServiceBinder = MusicPlayServiceInterface.Stub.asInterface(iBinder);
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.e("ziq", "bindService onServiceDisconnected: ");
            mBound = false;
        }
    };

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.e("ziq", "MusicPlayController onRequestPermissionsResult: ");
        switch (requestCode){
            case READ_EXTERNAL_STORAGE_REQUEST_ID:
                if(permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                    //获得权限
                    Toast.makeText(mContext, "获取了权限", Toast.LENGTH_SHORT).show();
                }else{
                    //拒绝
                    Toast.makeText(mContext, "拒绝权限", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    @Override
    public void onClick(View view) {
        if(ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            //拒绝之后会返回true
            if(ActivityCompat.shouldShowRequestPermissionRationale(mContext, Manifest.permission.READ_EXTERNAL_STORAGE)){
                Toast.makeText(mContext, "解释：该权限是获得本地音乐文件", Toast.LENGTH_SHORT).show();
            }
            //需要授权， 会回调activity中的onRequestPermissionsResult
            ActivityCompat.requestPermissions(mContext,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_EXTERNAL_STORAGE_REQUEST_ID);

            return;
        }
        try {
            switch (view.getId()){
                case R.id.img_music_list:
                    if(mServiceBinder != null){
                        mServiceBinder.setMusicList(MusicUtil.getLocalMusicList(mContext));
                    }
                    break;
                case R.id.img_music_play_controller:
                    if(mServiceBinder != null){
                        try {
                            if(mServiceBinder.isPlaying()){
                                mServiceBinder.pause();
                            }else{
                                mServiceBinder.play();
                            }
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case R.id.img_music_next:
                    if(mServiceBinder != null){
                        mServiceBinder.next();
                    }
                    break;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
