package com.example.musicinkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.musicinkotlin.activity.BaseActivity
import com.example.musicinkotlin.fragment.HomeFragment

class MainActivity : BaseActivity() {

    override fun getContainerId(): Int {
        return R.id.fl_content
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragment(HomeFragment(),"home")
    }
}
