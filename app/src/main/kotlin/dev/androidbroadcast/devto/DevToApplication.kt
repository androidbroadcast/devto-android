package dev.androidbroadcast.devto

import android.app.Application
import dev.androidbroadcast.devto.home.HomeFeature

class DevToApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .application(this)
            .apiKey(BuildConfig.DEVTO_API_KEY)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        HomeFeature.init(appComponent)
    }
}
