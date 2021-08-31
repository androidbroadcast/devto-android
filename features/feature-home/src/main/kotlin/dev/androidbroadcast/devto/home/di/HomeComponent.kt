package dev.androidbroadcast.devto.home.di

import dagger.Component
import dev.androidbroadcast.devto.api.DevtoApi
import dev.androidbroadcast.devto.home.HomeViewModel

@Component(dependencies = [HomeComponentDeps::class])
internal interface HomeComponent {

    val homeViewModelFactory: HomeViewModel.Factory
}

interface HomeComponentDeps {

    val devtoApi: DevtoApi
}
