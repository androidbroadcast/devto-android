// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.0.1")
        classpath(kotlin("gradle-plugin", version = libs.versions.kotlin.get()))
        classpath(kotlin("serialization", version = libs.versions.kotlin.get()))
    }
}

plugins {
    id("io.gitlab.arturbosch.detekt").version("1.18.1").apply(false)
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}