package com.example.musicinkotlin.service.musicService

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.support.v4.app.NotificationCompat
import android.widget.RemoteViews
import com.example.musicinkotlin.R
import java.lang.ref.WeakReference

/**
 * Created by john on 07/12/2017.
 */
class MusicNotification(musicPlayService: MusicPlayService) {
    private var mMusicPlayService : WeakReference<MusicPlayService>? = null
    private var mNotificationManager : NotificationManager? = null
    private var mNotification: Notification? = null
    private val mNotificationId = 1000

    init {
        mMusicPlayService = WeakReference(musicPlayService)
        mNotificationManager = mMusicPlayService?.get()?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    private fun getNotification(): Notification? {
        val musicPlayService = mMusicPlayService?.get()
        if (musicPlayService != null) {
            val remoteViews = RemoteViews(musicPlayService.getPackageName(), R.layout.notification)

            val musicInfo = musicPlayService.getCurrentMusicInfo()
            if (musicInfo != null) {
                remoteViews.setTextViewText(R.id.tv_music_name, musicInfo.name)
            }

            val stopIntent = Intent(MusicPlayService.REMOVE_NOTIFICATION_CMD)
            val stopPIntent = PendingIntent.getBroadcast(musicPlayService, 0, stopIntent, 0)
            remoteViews.setOnClickPendingIntent(R.id.iv_stop, stopPIntent)

            val nextIntent = Intent(MusicPlayService.NEXT_CMD)
            val nextPIntent = PendingIntent.getBroadcast(musicPlayService, 0, nextIntent, 0)
            remoteViews.setOnClickPendingIntent(R.id.iv_next, nextPIntent)


            val isPlaying = musicPlayService.isPlaying()

            val playOrPauseIntent = Intent(if (isPlaying) MusicPlayService.PAUSE_CMD else MusicPlayService.PLAY_CMD)
            val playOrPausePIntent = PendingIntent.getBroadcast(musicPlayService, 0, playOrPauseIntent, 0)
            remoteViews.setOnClickPendingIntent(R.id.iv_play_or_pause, playOrPausePIntent)
            val bitmap = BitmapFactory.decodeResource(musicPlayService.resources, if (isPlaying) R.mipmap.music_bottom_player_pause else R.mipmap.music_bottom_player_play)
            remoteViews.setImageViewBitmap(R.id.iv_play_or_pause, bitmap)

            if (mNotification == null) {
                val notificationBuilder = NotificationCompat.Builder(musicPlayService)
                        .setSmallIcon(R.drawable.ic_notification)
                        .setContent(remoteViews)
                mNotification = notificationBuilder.build()
            } else {
                mNotification!!.contentView = remoteViews
            }
        }

        return mNotification
    }

    fun updateNotification() {
        val musicPlayService = mMusicPlayService?.get()
        if (musicPlayService != null) {
            //提高程序进程 优先级， 不易被杀死
            musicPlayService.startForeground(mNotificationId, getNotification())
            mNotificationManager?.notify(mNotificationId, getNotification())
        }
    }


    fun removeNotification(): Unit {
        val musicPlayService = mMusicPlayService?.get()
        musicPlayService?.stopForeground(true)
        mNotificationManager?.cancel(mNotificationId)
    }


}