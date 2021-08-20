package dev.androidbroadcast.devto

import android.app.Application

class DevToApplication : Application() {

    val appComponent by lazy {
        DaggerAppComponent.builder()
            .application(this)
            .build()
    }
}
