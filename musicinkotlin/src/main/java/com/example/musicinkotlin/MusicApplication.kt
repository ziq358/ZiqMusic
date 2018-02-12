package com.example.musicinkotlin

import android.app.Application
import com.tencent.bugly.Bugly
import com.tencent.bugly.crashreport.CrashReport
import com.tencent.tinker.loader.app.TinkerApplication
import com.tencent.tinker.loader.shareutil.ShareConstants

/**
 * Created by john on 12/02/2018.
 */

public class MusicApplication : TinkerApplication{

    constructor() : super(ShareConstants.TINKER_ENABLE_ALL, "com.example.musicinkotlin.MusicApplicationLike", "com.tencent.tinker.loader.TinkerLoader", false) {

    }

}