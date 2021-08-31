package dev.androidbroadcast.devto.home

import dev.androidbroadcast.devto.home.di.DaggerHomeComponent
import dev.androidbroadcast.devto.home.di.HomeComponent
import dev.androidbroadcast.devto.home.di.HomeComponentDeps

object HomeFeature {

    private lateinit var homeComponentDeps: HomeComponentDeps

    internal val featureComponent: HomeComponent by lazy {
        DaggerHomeComponent.builder()
            .homeComponentDeps(homeComponentDeps)
            .build()
    }

    fun init(deps: HomeComponentDeps) {
        this.homeComponentDeps = deps
    }

}
