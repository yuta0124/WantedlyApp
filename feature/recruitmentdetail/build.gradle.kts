plugins {
    alias(libs.plugins.wantedlyapp.android.library.plugin)
    alias(libs.plugins.wantedlyapp.android.kotlin.plugin)
    alias(libs.plugins.wantedlyapp.android.compose.plugin)
    alias(libs.plugins.wantedlyapp.hilt.plugin)
    alias(libs.plugins.wantedlyapp.detekt)
}

android.namespace = "com.yuta0124.wantedlyapp.feature.recruitmentdetail"

dependencies {
    implementation(projects.core.designSystem)
    implementation(projects.core.ui)
    implementation(projects.core.model)
    implementation(projects.core.data)

    implementation(libs.androidx.appcompat)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
