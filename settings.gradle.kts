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

rootProject.name = "MapleMFinder"
include(":app")
include(":feature:main")
include(":core:domain")
include(":core:data")
include(":core:model")
include(":core:ui")
include(":core:designsystem")
include(":feature:search")
include(":feature:favorite")
include(":feature:detail")
