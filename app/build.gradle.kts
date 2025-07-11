plugins {
    alias(libs.plugins.wantedlyapp.android.gradle.plugin)
    alias(libs.plugins.wantedlyapp.android.kotlin.plugin)
    alias(libs.plugins.wantedlyapp.android.compose.plugin)
    alias(libs.plugins.wantedlyapp.hilt.plugin)
    alias(libs.plugins.wantedlyapp.detekt)
}

android {
    namespace = "com.yuta0124.wantedlyapp.app"

    defaultConfig {
        applicationId = "com.yuta0124.wantedlyapp"
        versionCode = 1
        versionName = "1.0"
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
}

dependencies {
    implementation(projects.core.designSystem)
    implementation(projects.feature.recruitments)
    implementation(projects.feature.favorite)
    implementation(projects.feature.recruitmentdetail)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
