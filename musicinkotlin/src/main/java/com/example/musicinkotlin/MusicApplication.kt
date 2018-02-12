package com.example.musicinkotlin

import android.app.Application
import com.tencent.bugly.crashreport.CrashReport

/**
 * Created by john on 12/02/2018.
 */

public class MusicApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        CrashReport.initCrashReport(applicationContext, "7d88acbfa6", true)
    }

}