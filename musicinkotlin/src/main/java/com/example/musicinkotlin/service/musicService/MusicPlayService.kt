package com.example.musicinkotlin.service.musicService

import android.app.Service
import android.content.*
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Handler
import android.os.IBinder
import com.example.musicinkotlin.MusicPlayServiceBinder
import com.example.musicinkotlin.R
import java.lang.ref.WeakReference
import android.os.SystemClock





/**
 * Created by john on 06/12/2017.
 */
class MusicPlayService: Service() {

    private val mMediaPlayer: MediaPlayer = MediaPlayer()
    private var mMusicNotification: MusicNotification? = null

    private var mMusicList: MutableList<MusicInfo>? = mutableListOf()
    private var mCurrentMusicPos: Int = 0
    private var mIsPause: Boolean = false
    private val mHandler: Handler = Handler()

    companion object {
        fun startMusicPlayService(context: Context) {
            val intent = Intent(context, MusicPlayService::class.java)
            context.startService(intent)
        }

        fun bindMusicPlayService(context: Context, serviceConnection: ServiceConnection) {
            //need to start service first
            val intent = Intent(context, MusicPlayService::class.java)
            context.bindService(intent, serviceConnection, 0)
        }

        val PLAY_CMD = "com.example.musicinkotlin.service.musicService.play"
        val NEXT_CMD = "com.example.musicinkotlin.service.musicService.next"
        val PAUSE_CMD = "com.example.musicinkotlin.service.musicService.pause"
        val STOP_CMD = "com.example.musicinkotlin.service.musicService.stop"
        val REMOVE_NOTIFICATION_CMD = "com.example.musicinkotlin.service.musicService.removeNotification"
        const val UPDATE_STATUS_CMD = "com.example.musicinkotlin.service.musicService.updateStatus"
    }

    override fun onCreate() {
        super.onCreate()
        mMusicNotification = MusicNotification(this)
        val intentFilter: IntentFilter = IntentFilter()
        intentFilter.addAction(PLAY_CMD)
        intentFilter.addAction(NEXT_CMD)
        intentFilter.addAction(PAUSE_CMD)
        intentFilter.addAction(STOP_CMD)
        intentFilter.addAction(REMOVE_NOTIFICATION_CMD)
        registerReceiver(mBroadcastReceiver, intentFilter)
    }

    //匿名内部类
    private var mBroadcastReceiver: BroadcastReceiver? = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            handleIntent(intent)
        }
    }

    fun handleIntent(intent: Intent?): Unit {
        val action = intent?.action
        when(action){
            PLAY_CMD -> play()
            NEXT_CMD -> next()
            PAUSE_CMD -> pause()
            STOP_CMD -> stop()
            REMOVE_NOTIFICATION_CMD ->{
                mMediaPlayer.stop()
                mMusicNotification?.removeNotification()
            }
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        handleIntent(intent)
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return MusicBinder(this)
    }

    override fun onDestroy() {
        unregisterReceiver(mBroadcastReceiver)
        super.onDestroy()
    }

    fun updateNotification(): Unit {
        mMusicNotification?.updateNotification()
        sendBroadcast(Intent(UPDATE_STATUS_CMD))
    }

    private val mTicker = object : Runnable {
        override fun run() {
            sendBroadcast(Intent(UPDATE_STATUS_CMD))
            val now = SystemClock.uptimeMillis()
            val next = now + (1000 - now % 1000)
            mHandler.postAtTime(this, next)
        }
    }


    fun play(): Unit {
        if(!mMediaPlayer.isPlaying){
            if(mIsPause){
                mIsPause = false
                mMediaPlayer.start()
                updateNotification()
            }else{
                playSong()
            }
        }
    }

    private fun playSong(): Unit {
        val musicUrl = getCurrentMusicInfo()?.uri
        mMediaPlayer.reset()
        mMediaPlayer.setDataSource(applicationContext, musicUrl)
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
        // 通过异步的方式装载媒体资源
        mMediaPlayer.prepareAsync()
        mMediaPlayer.setOnPreparedListener {
            mMediaPlayer.start()
            mHandler.post(mTicker)
            updateNotification()
        }
        mMediaPlayer.setOnCompletionListener(MediaPlayer.OnCompletionListener {
            next()
        })
    }

    fun next(): Unit {
        val count = mMusicList?.size!!
        mCurrentMusicPos++
        if(mCurrentMusicPos >= count){
            mCurrentMusicPos = 0
        }
        playSong()
    }

    fun getCurrentPosition(): Int {
        return 100 * mMediaPlayer.currentPosition / mMediaPlayer.duration
    }

    fun pause(): Unit {
        if (mMediaPlayer.isPlaying){
            mIsPause = true
            mMediaPlayer.pause()
            updateNotification()
        }
    }

    fun stop(): Unit {
        mMediaPlayer.stop()
        updateNotification()
    }

    fun isPlaying(): Boolean {
        return mMediaPlayer.isPlaying
    }

    fun setMusicList(musicList: MutableList<MusicInfo>?) {
        mMusicList = musicList
    }

    fun getCurrentMusicInfo(): MusicInfo? {
        return mMusicList?.get(mCurrentMusicPos)
    }


    class MusicBinder(): MusicPlayServiceBinder.Stub(){
        private var mService: WeakReference<MusicPlayService>? = null

        constructor(service: MusicPlayService) :this(){
            mService = WeakReference(service)
        }

        override fun play() {
            mService?.get()?.play()
        }

        override fun next() {
            mService?.get()?.next()
        }

        override fun getCurrentPosition(): Int {
            return mService?.get()?.getCurrentPosition()!!
        }

        override fun pause() {
            mService?.get()?.pause()
        }

        override fun stop() {
            mService?.get()?.stop()
        }

        override fun isPlaying(): Boolean {
            return mService?.get()?.isPlaying()!!
        }

        override fun getCurrentMusicInfo(): MusicInfo? {
            return mService?.get()?.getCurrentMusicInfo()
        }

        override fun setMusicList(musicList: MutableList<MusicInfo>?) {
            mService?.get()?.setMusicList(musicList)
        }
    }



}