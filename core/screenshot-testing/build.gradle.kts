import com.github.takahirom.roborazzi.ExperimentalRoborazziApi

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.wantedlyapp.detekt)
    alias(libs.plugins.roborazzi.plugin)
}

android {
    namespace = "com.yuta0124.wantedlyapp.core.screenshot.testing"
    compileSdk = 36

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    testImplementation(libs.roborazzi)
    testImplementation(libs.roborazzi.compose)
    testImplementation(libs.roborazzi.preview.scanner.support)
    testImplementation(libs.composable.preview.scanner)
    testImplementation(libs.robolectric)
}

roborazzi {
    outputDir.set(rootProject.file("screenshots/build/outputs/roborazzi"))

    @OptIn(ExperimentalRoborazziApi::class)
    generateComposePreviewRobolectricTests {
        enable = true
        packages = listOf("com.example.basecomposeproject")
    }
}
