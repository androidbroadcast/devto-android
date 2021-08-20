package dev.androidbroadcast.devto

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Scope

@[Component(modules = [AppModule::class]) AppScope]
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class AppScope

@Module
class AppModule
