package com.xogrp.john.music.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.IntDef;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.widget.RemoteViews;

import com.xogrp.john.music.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2017/4/30 0030.
 */

public class MusicNotification {
    private WeakReference<MusicPlayService> musicPlayServiceWeakReference;
    private NotificationManager mNotificationManager;
    private Notification mNotification;
    private int mNotificationId = 1000;

    private static final int NOTIFY_MODE_NONE = 0;
    private static final int NOTIFY_MODE_FOREGROUND = 1;
    private static final int NOTIFY_MODE_BACKGROUNG = 2;
    @IntDef({NOTIFY_MODE_BACKGROUNG, NOTIFY_MODE_FOREGROUND})
    @Retention(RetentionPolicy.SOURCE)
    @interface NotifyMode{}

    private @NotifyMode int mNotifyMode = NOTIFY_MODE_NONE;

    public MusicNotification(MusicPlayService musicPlayService) {
        musicPlayServiceWeakReference = new WeakReference<>(musicPlayService);
        mNotificationManager = (NotificationManager) musicPlayService.getSystemService(Context.NOTIFICATION_SERVICE);
    }


    private Notification getNotification(){
        MusicPlayService musicPlayService = musicPlayServiceWeakReference.get();
        if(musicPlayService != null){
            RemoteViews remoteViews = new RemoteViews(musicPlayService.getPackageName(), R.layout.notification);

            MusicInfo musicInfo = musicPlayService.getCurrentMusicInfo();
            if(musicInfo != null){
                remoteViews.setTextViewText(R.id.tv_music_name, musicInfo.musicName);
                remoteViews.setTextViewText(R.id.tv_music_singer, musicInfo.artist);
            }

            Intent stopIntent = new Intent(MusicPlayService.STOP_CMD);
            PendingIntent stopPIntent = PendingIntent.getBroadcast(musicPlayService, 0, stopIntent, 0);
            remoteViews.setOnClickPendingIntent(R.id.iv_stop, stopPIntent);

            Intent nextIntent = new Intent(MusicPlayService.NEXT_CMD);
            PendingIntent nextPIntent = PendingIntent.getBroadcast(musicPlayService, 0, nextIntent, 0);
            remoteViews.setOnClickPendingIntent(R.id.iv_next, nextPIntent);


            Intent playOrPauseIntent = new Intent(musicPlayService.isPlaying() ? MusicPlayService.PAUSE_CMD : MusicPlayService.PLAY_CMD );
            PendingIntent playOrPausePIntent = PendingIntent.getBroadcast(musicPlayService, 0, playOrPauseIntent, 0);
            remoteViews.setOnClickPendingIntent(R.id.iv_play_or_pause, playOrPausePIntent);
            Bitmap bitmap = BitmapFactory.decodeResource(musicPlayService.getResources(), musicPlayService.isPlaying() ? R.mipmap.music_bottom_player_pause : R.mipmap.music_bottom_player_play);
            remoteViews.setImageViewBitmap(R.id.iv_play_or_pause, bitmap);

            if(mNotification == null){
                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(musicPlayService)
                        .setSmallIcon(R.drawable.ic_notification)
                        .setOngoing(true)
                        .setContent(remoteViews);
                mNotification = notificationBuilder.build();
            }else {
                mNotification.contentView = remoteViews;
            }
        }

        return mNotification;
    }

    public void updateNotification(){
        MusicPlayService musicPlayService = musicPlayServiceWeakReference.get();
        if(musicPlayService != null){
            mNotifyMode = musicPlayService.isPlaying() ? NOTIFY_MODE_FOREGROUND : NOTIFY_MODE_BACKGROUNG;
            if (mNotifyMode == NOTIFY_MODE_FOREGROUND) {
                musicPlayService.startForeground(mNotificationId, getNotification());
            } else if (mNotifyMode == NOTIFY_MODE_BACKGROUNG) {
                 mNotificationManager.notify(mNotificationId, getNotification());
            }
        }
    }

    public void cancelNotification(){
        MusicPlayService musicPlayService = musicPlayServiceWeakReference.get();
        if(musicPlayService != null){
            musicPlayService.stopForeground(true);
        }
        mNotificationManager.cancel(mNotificationId);
    }

}
