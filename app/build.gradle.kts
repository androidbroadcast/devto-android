import java.util.Properties
import kotlin.checkNotNull

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("io.gitlab.arturbosch.detekt")
}

val localProps = Properties()
val localProperties = File(rootProject.rootDir, "local.properties")
if (localProperties.exists() && localProperties.isFile) {
    localProperties.inputStream().use { input ->
        localProps.load(input)
    }
}

android {
    compileSdk = 30

    defaultConfig {
        applicationId = "dev.androidbroadcast.devto"
        minSdk = 21
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        val devtoApiKey = checkNotNull(localProps.getProperty("devto.apikey") ?: System.getenv("DEVTO_API_KEY"))
        buildConfigField("String", "DEVTO_API_KEY", "\"$devtoApiKey\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            packagingOptions {
                resources.excludes += "DebugProbesKt.bin"
            }
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

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":devto-api"))

    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.google.material)

    implementation(libs.bundles.androidx.compose)
    implementation(libs.bundles.androidx.lifecycle)

    implementation(libs.dagger.runtime)
    kapt(libs.dagger.compiler)

    testImplementation(libs.junit4)

    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.bundles.androidx.test.espresso)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)

    debugImplementation(libs.androidx.compose.ui.tooling)
}
