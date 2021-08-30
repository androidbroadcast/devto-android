package dev.androidbroadcast.devto

import android.app.Application

class DevToApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .application(this)
            .apiKey(BuildConfig.DEVTO_API_KEY)
            .build()
    }
}
