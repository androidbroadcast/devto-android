plugins {
    id("org.jetbrains.kotlin.jvm")
    id("kotlinx-serialization")
    id("io.gitlab.arturbosch.detekt")
}

dependencies {
    implementation(libs.bundles.retrofit2)
    implementation(libs.androidx.annotation)
}