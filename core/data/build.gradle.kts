plugins {
    alias(libs.plugins.wantedlyapp.android.library.plugin)
    alias(libs.plugins.wantedlyapp.android.kotlin.plugin)
    alias(libs.plugins.wantedlyapp.hilt.plugin)
    alias(libs.plugins.wantedlyapp.detekt)
}

android {
    namespace = "com.yuta0124.wantedlyapp.core.data"

    defaultConfig {
        buildConfigField("String", "BASE_URL", "\"https://www.wantedly.com/api/v1/\"")
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(projects.core.common)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.ktor.core)
    implementation(libs.ktor.android)
    implementation(libs.ktor.logging)
    implementation(libs.ktor.content.negotiation)
    implementation(libs.ktor.kotlin.serialization)
}
