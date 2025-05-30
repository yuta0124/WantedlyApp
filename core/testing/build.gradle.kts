plugins {
    alias(libs.plugins.wantedlyapp.android.library.plugin)
    alias(libs.plugins.wantedlyapp.android.kotlin.plugin)
    alias(libs.plugins.wantedlyapp.detekt)
}

android.namespace = "com.yuta0124.wantedlyapp.core.testing"

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
