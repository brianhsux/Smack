package com.example.brianhsu.smack.Controller

import android.app.Application
import com.example.brianhsu.smack.Utilities.SharePrefs

/**
 * Created by brian on 2018/4/12.
 */
class App: Application() {

    companion object {
        lateinit var prefs: SharePrefs
    }

    override fun onCreate() {
        prefs = SharePrefs(applicationContext)
        super.onCreate()
    }
}