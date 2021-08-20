plugins {
    id("com.android.library")
    id("kotlin-android")
    id("io.gitlab.arturbosch.detekt")
}

android {
    compileSdk = 30

    defaultConfig {
        minSdk = 21
        targetSdk = 30

        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.6.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("com.google.android.material:material:1.4.0")
}