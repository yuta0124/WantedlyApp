pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "WantedlyApp"
include(
    ":app",
    ":ui-catalog",
    ":feature",
    ":feature:recruitments",
    ":feature:recruitmentdetail",
    ":feature:favorite",
    ":core",
    ":core:ui",
    ":core:data",
    ":core:model",
    ":core:common",
    ":core:testing",
    ":core:design-system",
    ":core:screenshot-testing",
)
