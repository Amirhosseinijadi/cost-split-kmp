pluginManagement {
    includeBuild("build-logic")

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "cost-split-kmp"

include(
    ":composeApp",
    ":core:common",
    ":core:network",
    ":core:ui",
    ":feature:activity",
    ":feature:expenses",
    ":feature:groups",
    ":feature:home",
    ":feature:settings",
)
