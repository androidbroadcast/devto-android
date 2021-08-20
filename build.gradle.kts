// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val libs = project.extensions.getByType<VersionCatalogsExtension>().named("libs") as org.gradle.accessors.dm.LibrariesForLibs

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.0.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21")
        // classpath("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.18.0")
    }
}

plugins {
    id("io.gitlab.arturbosch.detekt").version("1.18.0").apply(false)
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}