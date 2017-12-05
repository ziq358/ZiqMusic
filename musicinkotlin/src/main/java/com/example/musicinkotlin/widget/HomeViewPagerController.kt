package com.example.musicinkotlin.widget

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import com.example.musicinkotlin.fragment.DiscoverFragment
import com.example.musicinkotlin.fragment.FriendsFragment
import com.example.musicinkotlin.fragment.MusicFragment
import java.util.ArrayList

/**
 * Created by john on 05/12/2017.
 */
class HomeViewPagerController (viewPager: ViewPager?, fm: FragmentManager){

    private val mViewPager: ViewPager? = viewPager
    private val mFragmentArrayList: ArrayList<Fragment> = ArrayList()

    init {
        mFragmentArrayList.add(DiscoverFragment())
        mFragmentArrayList.add(MusicFragment())
        mFragmentArrayList.add(FriendsFragment())
        val adapter = HomeViewPagerAdapter(fm, mFragmentArrayList)
        mViewPager?.adapter = adapter
    }


    fun setCurrentItem(position: Int, smoothScroll: Boolean) {
        mViewPager?.setCurrentItem(position, smoothScroll)
    }

    fun addOnPageChangeListener(listener: ViewPager.OnPageChangeListener) {
        mViewPager?.addOnPageChangeListener(listener)
    }

    internal inner class HomeViewPagerAdapter(fm: FragmentManager, private val mMusicFragmentArrayList: ArrayList<Fragment>?) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment? {
            return if (mMusicFragmentArrayList == null) null else mMusicFragmentArrayList[position]
        }

        override fun getCount(): Int {
            return mMusicFragmentArrayList?.size ?: 0
        }
    }
}