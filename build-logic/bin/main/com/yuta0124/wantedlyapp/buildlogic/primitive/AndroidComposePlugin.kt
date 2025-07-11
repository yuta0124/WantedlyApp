package com.yuta0124.wantedlyapp.buildlogic.primitive

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidComposePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.devtools.ksp")
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            android {
                buildFeatures.compose = true
            }

            dependencies {
                implementation(platform(libs.library("androidx-compose-bom")))
                implementation(libs.library("androidx-core-ktx"))
                implementation(libs.library("androidx-activity-compose"))
                implementation(libs.library("androidx-lifecycle-runtime-ktx"))
                implementation(libs.library("androidx-lifecycle-runtime-compose"))
                implementation(libs.library("androidx-ui"))
                implementation(libs.library("androidx-material3"))
                implementation(libs.library("androidx-ui-tooling-preview"))
                implementation(libs.library("androidx-lifecycle-runtime-ktx"))
                implementation(libs.library("androidx-activity-compose"))
                implementation(libs.library("navigation-compose"))
                implementation(libs.library("hilt-navigation-compose"))
                implementation(libs.library("coil-compose"))
                implementation(libs.library("coil-network-okhttp"))
                testImplementation(libs.library("androidx-ui-test-junit4"))
                debugImplementation(libs.library("androidx-ui-tooling"))
                debugImplementation(libs.library("androidx-ui-test-manifest"))
            }
        }
    }
}