// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.0.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${libs.versions.kotlin.get()}")
        classpath(kotlin("serialization", version = libs.versions.kotlin.get()))
        classpath(libs.dagger.hilt.agp)
    }
}

plugins {
    id("io.gitlab.arturbosch.detekt").version("1.18.1").apply(false)
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
