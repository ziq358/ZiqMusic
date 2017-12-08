package com.example.musicinkotlin.widget

import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import com.example.musicinkotlin.R

/**
 * Created by john on 04/12/2017.
 */
class DrawerLayoutController private constructor() : NavigationView.OnNavigationItemSelectedListener {

    private var mDrawerLayout: DrawerLayout? = null
    private var mLeftNavigator: LeftNavigator? = null
    private var mNavigationView : NavigationView? = null

    constructor(drawerLayout: DrawerLayout, leftNavigator: LeftNavigator) : this(){
        mDrawerLayout = drawerLayout
        mLeftNavigator = leftNavigator
        mNavigationView = mDrawerLayout?.findViewById(R.id.nav_view)
        mNavigationView?.inflateMenu(R.menu.left_nav_menu)
        mNavigationView?.inflateHeaderView(R.layout.left_nav_header)
        val footer: FrameLayout? = mNavigationView?.findViewById(R.id.nav_view_footer)
        footer?.addView(LayoutInflater.from(mNavigationView?.context).inflate(R.layout.left_nav_footer, mNavigationView, false))
        mNavigationView?.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.left_nav_my_msg -> mLeftNavigator?.gotoMyMessage()
            R.id.left_nav_vip_center -> mLeftNavigator?.gotoVipCenter()
        }
        return true
    }

    fun openDrawer(){
        mDrawerLayout?.openDrawer(GravityCompat.START)
    }

    fun closeDrawer() {
        mDrawerLayout?.closeDrawer(GravityCompat.START)
    }

    interface LeftNavigator{
        fun gotoMyMessage()
        fun gotoVipCenter()
        fun openDrawer()
        fun closeDrawer()
    }

}