package dev.androidbroadcast.devto

import android.content.Context
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.androidbroadcast.devto.api.DevtoApi
import dev.androidbroadcast.devto.api.DevtoApiKeyProvider
import okhttp3.OkHttpClient
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
class BuildTypeModule {

    @[Provides Singleton]
    fun provideNetworkFlipperPlugin() = NetworkFlipperPlugin()

    @[Provides Singleton]
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        networkFlipperPlugin: NetworkFlipperPlugin
    ): OkHttpClient {
        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(context)) {
            return OkHttpClient.Builder()
                .addNetworkInterceptor(FlipperOkhttpInterceptor(networkFlipperPlugin))
                .build()
        } else {
            return OkHttpClient()
        }
    }
}
