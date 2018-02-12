package com.example.musicinkotlin.activity

import android.content.ComponentName
import android.content.ServiceConnection
import android.net.Uri
import android.os.Bundle
import android.os.IBinder
import android.support.v4.app.Fragment
import android.view.View
import android.widget.Toast
import com.example.musicinkotlin.MusicPlayServiceBinder
import com.example.musicinkotlin.R
import com.example.musicinkotlin.fragment.HomeFragment
import com.example.musicinkotlin.service.musicService.MusicInfo
import com.example.musicinkotlin.service.musicService.MusicPlayService
import com.example.musicinkotlin.widget.DrawerLayoutController
import com.example.musicinkotlin.widget.MusicPlayerController
import com.tencent.bugly.crashreport.CrashReport
import kotlinx.android.synthetic.main.song_list_load_more_layout.*

class MainActivity : BaseActivity(), DrawerLayoutController.LeftNavigator {

    private var mDrawerLayoutController: DrawerLayoutController? = null
    private var mMusicPlayerController: MusicPlayerController? =null

    override fun getContainerId(): Int {
        return R.id.fl_content
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mDrawerLayoutController = DrawerLayoutController(findViewById(R.id.drawer_layout), this)
        replaceFragment(HomeFragment(),"home")
        mMusicPlayerController = MusicPlayerController(findViewById(R.id.fl_bottom_player), this)
    }

    override fun addFragment(fragment: Fragment, tag: String) {
        super.addFragment(fragment, tag)
    }

    override fun gotoMyMessage() {
        Toast.makeText(baseContext, "热更新修复", Toast.LENGTH_SHORT).show()
//        CrashReport.testJavaCrash()
    }

    override fun gotoVipCenter() {
    }

    override fun openDrawer() {
        mDrawerLayoutController?.openDrawer()
    }

    override fun closeDrawer() {
        mDrawerLayoutController?.closeDrawer()
    }

    override fun onDestroy() {
        mMusicPlayerController?.stopMusicPlayService(this)
        mMusicPlayerController?.unbindMusicPlayService()

        super.onDestroy()
    }
}
