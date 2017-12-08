package com.example.musicinkotlin.activity

import android.content.ComponentName
import android.content.ServiceConnection
import android.net.Uri
import android.os.Bundle
import android.os.IBinder
import android.support.v4.app.Fragment
import android.widget.Toast
import com.example.musicinkotlin.MusicPlayServiceBinder
import com.example.musicinkotlin.R
import com.example.musicinkotlin.fragment.HomeFragment
import com.example.musicinkotlin.service.musicService.MusicInfo
import com.example.musicinkotlin.service.musicService.MusicPlayService
import com.example.musicinkotlin.widget.DrawerLayoutController

class MainActivity : BaseActivity(), DrawerLayoutController.LeftNavigator {

    private var mDrawerLayoutController: DrawerLayoutController? = null

    private val mServiceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            mMusicServiceBinder = null
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Toast.makeText(this@MainActivity,"bind music service", Toast.LENGTH_SHORT).show()
            mMusicServiceBinder = MusicPlayServiceBinder.Stub.asInterface(service)
        }
    }
    private var mMusicServiceBinder: MusicPlayServiceBinder? = null


    override fun getContainerId(): Int {
        return R.id.fl_content
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mDrawerLayoutController = DrawerLayoutController(findViewById(R.id.drawer_layout), this)
        replaceFragment(HomeFragment(),"home")
    }

    override fun addFragment(fragment: Fragment, tag: String) {
        super.addFragment(fragment, tag)
    }

    override fun gotoMyMessage() {
        MusicPlayService.startMusicPlayService(this)
        MusicPlayService.bindMusicPlayService(this, mServiceConnection)
    }

    override fun gotoVipCenter() {
        val songList = mutableListOf<MusicInfo>()
        songList.add(MusicInfo("我们不一样", Uri.parse("android.resource://" + applicationContext.packageName + "/" + R.raw.wo_men_bu_yi_yang)))
        songList.add(MusicInfo("刚好遇见你", Uri.parse("android.resource://" + applicationContext.packageName + "/" + R.raw.gang_hao_yu_jian_ni)))
        songList.add(MusicInfo("带你去旅行", Uri.parse("android.resource://" + applicationContext.packageName + "/" + R.raw.dai_ni_qu_lu_xing)))
        songList.add(MusicInfo("演员", Uri.parse("android.resource://" + applicationContext.packageName + "/" + R.raw.yan_yuan)))
        mMusicServiceBinder?.setMusicList(songList)
        mMusicServiceBinder?.play()
    }

    override fun openDrawer() {
        mDrawerLayoutController?.openDrawer()
    }

    override fun closeDrawer() {
        mDrawerLayoutController?.closeDrawer()
    }

    override fun onDestroy() {
        unbindService(mServiceConnection)
        mMusicServiceBinder = null
        super.onDestroy()
    }
}
