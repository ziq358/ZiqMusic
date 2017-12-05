package com.example.musicinkotlin.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.musicinkotlin.R
import com.example.musicinkotlin.widget.DrawerLayoutController
import com.example.musicinkotlin.widget.HomeTabController
import com.example.musicinkotlin.widget.HomeViewPagerController

/**
 * Created by john on 05/12/2017.
 */
open class HomeFragment :Fragment(), HomeTabController.HomeTabListener {

    private var mTabHostController: HomeTabController? = null
    private var mHomeViewPagerController: HomeViewPagerController? = null

    private var mLeftNavigator: DrawerLayoutController.LeftNavigator? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(context is DrawerLayoutController.LeftNavigator){
            mLeftNavigator = context
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        mTabHostController = HomeTabController(view, this)
        mHomeViewPagerController = HomeViewPagerController(view?.findViewById(R.id.vp_content), fragmentManager)
        val toolbar = view?.findViewById<View>(R.id.toolbar) as Toolbar
        initToolbar(toolbar)
    }

    override fun onMusicTab() {
        mHomeViewPagerController?.setCurrentItem(1, false)
    }

    override fun onFriendsTab() {
        mHomeViewPagerController?.setCurrentItem(2, false)
    }

    override fun onDiscoverTab() {
        mHomeViewPagerController?.setCurrentItem(0, false)
    }

    private fun initToolbar(toolbar: Toolbar) {
        toolbar.setNavigationIcon(R.mipmap.ic_hamberger)
        toolbar.setNavigationOnClickListener {
            mLeftNavigator?.openDrawer()
        }
    }

}