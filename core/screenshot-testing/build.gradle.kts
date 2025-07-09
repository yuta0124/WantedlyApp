import com.github.takahirom.roborazzi.ExperimentalRoborazziApi

plugins {
    alias(libs.plugins.wantedlyapp.android.library.plugin)
    alias(libs.plugins.wantedlyapp.android.kotlin.plugin)
    alias(libs.plugins.wantedlyapp.detekt)
    alias(libs.plugins.roborazzi.plugin)
}

android {
    namespace = "com.yuta0124.wantedlyapp.core.screenshot.testing"

    testOptions {
        unitTests {
            // Whether unmocked methods from android.jar should throw exceptions or return default values
            isIncludeAndroidResources = true
            // Configures all unit testing tasks.
            all {
                it.systemProperties(
                    "roborazzi.output.dir" to rootProject.file("screenshots").absolutePath,
                    "robolectric.pixelCopyRenderMode" to "hardware",
                )
            }
        }
    }
}

dependencies {
    implementation(projects.feature.recruitments)
    implementation(projects.feature.recruitmentdetail)
    implementation(projects.feature.favorite)

    implementation(libs.androidx.appcompat)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    testImplementation(libs.roborazzi)
    testImplementation(libs.roborazzi.compose)
    testImplementation(libs.roborazzi.preview.scanner.support)
    testImplementation(libs.composable.preview.scanner)
    testImplementation(libs.robolectric)
    testImplementation(libs.junit.vintage.engine)
}

roborazzi {
    outputDir.set(rootProject.file("screenshots/build/outputs/roborazzi"))

    @OptIn(ExperimentalRoborazziApi::class)
    generateComposePreviewRobolectricTests {
        enable = true
        packages = listOf("com.yuta0124.wantedlyapp")
    }
}
