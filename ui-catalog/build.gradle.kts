plugins {
    alias(libs.plugins.wantedlyapp.android.gradle.plugin)
    alias(libs.plugins.wantedlyapp.android.kotlin.plugin)
    alias(libs.plugins.wantedlyapp.android.compose.plugin)
    alias(libs.plugins.wantedlyapp.detekt)
}

android {
    namespace = "com.yuta0124.wantedlyapp.ui.catalog"

    defaultConfig {
        applicationId = "com.yuta0124.wantedlyapp.ui.catalog"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            signingConfig = signingConfigs.named("debug").get()
        }
    }
}

dependencies {
    implementation(projects.core.designSystem)
    implementation(projects.core.ui)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
