package com.ashu.ocotopus

import android.app.Application
import com.ashu.appanalytics.AnalyzeApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class OctopusApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        AnalyzeApp.init(this)
    }
}