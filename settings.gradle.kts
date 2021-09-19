dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

enableFeaturePreview("VERSION_CATALOGS")

rootProject.name = "dev.to"
include(":app")
include(":core")
include(":devto-api")
include(":features:feature-home")
include(":features:feature-user")
include(":devto-theme")
