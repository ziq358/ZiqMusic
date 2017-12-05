package com.example.musicinkotlin.widget

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v4.view.ViewCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.musicinkotlin.R

/**
 * Created by john on 05/12/2017.
 */
class HomeTabController(view: View?, homeTabListener: HomeTabListener) {
    private val mContext: Context? = view?.context
    private var mLayoutInflater: LayoutInflater? = LayoutInflater.from(mContext)
    private var mTabLinearLayout: LinearLayout? = null
    private var mHomeTabListener: HomeTabListener? = homeTabListener


    init {
        mTabLinearLayout = view?.findViewById(R.id.sv_ll_tab)
        val discoverTabInfo = DiscoverTabInfo()
        val musicTabInfo = MusicTabInfo()
        val friendsTabInfo = FriendsTabInfo()
        mTabLinearLayout?.addView(discoverTabInfo.createView(mTabLinearLayout))
        mTabLinearLayout?.addView(musicTabInfo.createView(mTabLinearLayout))
        mTabLinearLayout?.addView(friendsTabInfo.createView(mTabLinearLayout))
    }

    interface HomeTabListener {
        fun onDiscoverTab()
        fun onMusicTab()
        fun onFriendsTab()
    }


    private abstract inner class TabInfo : View.OnClickListener {

        internal abstract val drawableId: Int
        public fun createView(parentView: ViewGroup?): View {
            val img = mLayoutInflater?.inflate(R.layout.home_toolbar_tab_item, parentView, false) as ImageView
            val drawable = ContextCompat.getDrawable(mContext, drawableId)
            DrawableCompat.setTintList(drawable, ContextCompat.getColorStateList(mContext, R.color.selector_home_tab_item_icon))
            ViewCompat.setBackground(img, drawable)
            img.setOnClickListener(this)
            return img
        }
    }

    private inner class DiscoverTabInfo : TabInfo() {
        override val drawableId: Int get() = R.mipmap.actionbar_discover_selected
        override fun onClick(view: View) {
                mHomeTabListener?.onDiscoverTab()
        }

    }

    private inner class MusicTabInfo : TabInfo() {
        override val drawableId: Int get() = R.mipmap.actionbar_music_selected
        override fun onClick(view: View) {
                mHomeTabListener?.onMusicTab()
        }
    }

    private inner class FriendsTabInfo : TabInfo() {
        override val drawableId: Int get() = R.mipmap.actionbar_friends_selected
        override fun onClick(view: View) {
            mHomeTabListener?.onFriendsTab();
        }
    }

}