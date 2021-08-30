package dev.androidbroadcast.devto

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dev.androidbroadcast.devto.api.DevtoApi
import javax.inject.Qualifier
import javax.inject.Scope

@[Component(modules = [AppModule::class]) AppScope]
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun apiKey(@ApiKey apiKey: String): Builder

        fun build(): AppComponent
    }
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.VALUE_PARAMETER)
annotation class ApiKey

@Scope
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class AppScope

@Module
class AppModule {

    @AppScope
    @Provides
    fun providerDevApi(@ApiKey apiKey: String) = DevtoApi(apiKey)
}
