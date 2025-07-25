// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.roborazzi.plugin) apply false
    alias(libs.plugins.hilt.gradle.plugin) apply false
    alias(libs.plugins.ksp) apply false
}

subprojects {
    plugins.withId("com.android.library") {
        tasks.withType<Test>().configureEach {
            useJUnitPlatform()
        }
    }
}