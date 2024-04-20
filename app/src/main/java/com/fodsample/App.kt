package com.fodsample

import android.app.Application
//Import this from the gBeat library
import com.bpmsync.gbeat.util.SharedPrefsManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()

        SharedPrefsManager.initialize(this)
    }
}
