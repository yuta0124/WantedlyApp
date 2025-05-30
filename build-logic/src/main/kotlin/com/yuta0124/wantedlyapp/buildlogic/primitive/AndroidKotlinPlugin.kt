package com.yuta0124.wantedlyapp.buildlogic.primitive

import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidKotlinPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.plugin.serialization")
                apply("com.google.devtools.ksp")
            }

            tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java) {
                kotlinOptions.jvmTarget = "17"
            }

            android {
                kotlinOptions {
                    allWarningsAsErrors = properties["warningsAsErrors"] as? Boolean ?: false
                    freeCompilerArgs = freeCompilerArgs + listOf("-Xcontext-receivers")
                    jvmTarget = JavaVersion.VERSION_17.toString()
                }
            }

            dependencies {
                implementation(libs.library("arrow-core"))
                implementation(libs.library("kotlin-serialization-json"))
            }
        }
    }
}