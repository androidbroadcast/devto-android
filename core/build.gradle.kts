plugins {
    id("com.android.library")
    id("kotlin-android")
    id("io.gitlab.arturbosch.detekt")
}

android {
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.tagetSdk.get().toInt()

        vectorDrawables {
            useSupportLibrary = true
        }

        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
        }
    }
}

dependencies {
    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.google.material)
}
