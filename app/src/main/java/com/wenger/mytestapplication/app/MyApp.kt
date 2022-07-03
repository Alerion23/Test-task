package com.wenger.mytestapplication.app

import android.app.Application
import com.appsflyer.AppsFlyerLib
import com.onesignal.OneSignal
import com.wenger.mytestapplication.utils.Constants
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        AppsFlyerLib.getInstance().init(Constants.AF_DEV_KEY, null, this)
        AppsFlyerLib.getInstance().start(this)
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        OneSignal.initWithContext(this)
        OneSignal.setAppId(Constants.ONESIGNAL_APP_ID)

    }
}