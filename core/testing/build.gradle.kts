plugins {
    alias(libs.plugins.wantedlyapp.android.library.plugin)
    alias(libs.plugins.wantedlyapp.android.kotlin.plugin)
    alias(libs.plugins.wantedlyapp.hilt.plugin)
    alias(libs.plugins.wantedlyapp.detekt)
}

android.namespace = "com.yuta0124.wantedlyapp.core.testing"

dependencies {
    api(libs.junit)
    api(libs.androidx.junit)
    api(libs.androidx.espresso.core)
    api(libs.kotest.assertions.core)
    api(libs.kotest.runner.junit5)
}
