package com.example.musicinkotlin.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

/**
 * Created by john on 04/12/2017.
 */
abstract class BaseActivity : AppCompatActivity(){

    // FragmentManager? 表示 类型，且可以为null
    private var mFragmentManager: FragmentManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mFragmentManager = supportFragmentManager
    }

    // unit 相当于 void 返回
    fun addFragment(fragment: Fragment, tag: String): Unit {
        var containerId = getContainerId()
        if(containerId > 0){
            var transaction = mFragmentManager?.beginTransaction()
            transaction?.add(containerId, fragment, tag)
            transaction?.addToBackStack(tag)
            transaction?.commitAllowingStateLoss()
        }else{
            Toast.makeText(this, "container id error", Toast.LENGTH_SHORT).show()
        }
    }

    abstract fun getContainerId(): Int

}