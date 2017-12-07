package com.example.musicinkotlin.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.Toast
import com.example.musicinkotlin.R
import com.example.musicinkotlin.fragment.HomeFragment
import com.example.musicinkotlin.service.musicService.MusicPlayService
import com.example.musicinkotlin.widget.DrawerLayoutController

class MainActivity : BaseActivity(), DrawerLayoutController.LeftNavigator {

    private var mDrawerLayoutController: DrawerLayoutController? = null

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
        Toast.makeText(this,"play music", Toast.LENGTH_SHORT).show()
        MusicPlayService.startMusicPlayService(this)
    }

    override fun openDrawer() {
        mDrawerLayoutController?.openDrawer()
    }

    override fun closeDrawer() {
        mDrawerLayoutController?.closeDrawer()
    }
}
