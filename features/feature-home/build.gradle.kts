plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("io.gitlab.arturbosch.detekt")
}

android {
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.tagetSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.get()
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":devto-api"))
    implementation(project(":devto-theme"))

    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.bundles.kotlinx.coroutines)
    implementation(libs.bundles.androidx.lifecycle)
    implementation(libs.bundles.androidx.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.coil.compose)
    implementation(libs.google.accompanist.insets)

    debugImplementation(libs.androidx.compose.ui.tooling)

    implementation(libs.dagger.runtime)
    kapt(libs.dagger.compiler)
}
