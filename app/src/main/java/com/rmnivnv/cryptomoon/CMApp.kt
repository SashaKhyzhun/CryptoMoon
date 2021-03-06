package com.rmnivnv.cryptomoon

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.res.Configuration
import com.rmnivnv.cryptomoon.di.DaggerAppComponent
import com.rmnivnv.cryptomoon.model.LocaleManager
import com.squareup.leakcanary.LeakCanary
import com.twitter.sdk.android.core.Twitter
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by rmnivnv on 05/07/2017.
 */


class CMApp : Application(), HasActivityInjector {

    @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) return
        LeakCanary.install(this)

        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this)

        Twitter.initialize(this)
    }

    override fun activityInjector() = dispatchingAndroidInjector

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(LocaleManager.setLocale(base!!))
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        LocaleManager.setLocale(this)
    }
}