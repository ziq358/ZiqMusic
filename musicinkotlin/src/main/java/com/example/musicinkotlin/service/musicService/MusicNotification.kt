package com.example.musicinkotlin.service.musicService

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
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
//            musicPlayService.startForeground(mNotificationId, getNotification())
//            mNotificationManager?.notify(mNotificationId, getNotification())
        }
    }

}