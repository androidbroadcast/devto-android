package dev.androidbroadcast.devto

import android.app.Application
import coil.Coil
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.core.FlipperClient
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.soloader.SoLoader
import dagger.hilt.android.HiltAndroidApp
import okhttp3.OkHttpClient
import javax.inject.Inject
import javax.inject.Provider

@HiltAndroidApp
class DevToApplication : Application() {

    @Inject
    fun setupFlipper(networkFlipperPlugin: Provider<NetworkFlipperPlugin>) {
        SoLoader.init(this, false)
        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(this)) {
            val client: FlipperClient = AndroidFlipperClient.getInstance(this)
            client.addPlugin(InspectorFlipperPlugin(this, DescriptorMapping.withDefaults()))
            client.addPlugin(networkFlipperPlugin.get())
            client.start()
        }
    }

    @Inject
    internal fun setupCoil(okHttpClient: OkHttpClient) {
        Coil.setImageLoader(
            Coil.imageLoader(this)
                .newBuilder()
                .okHttpClient(okHttpClient)
                .build()
        )
    }
}
