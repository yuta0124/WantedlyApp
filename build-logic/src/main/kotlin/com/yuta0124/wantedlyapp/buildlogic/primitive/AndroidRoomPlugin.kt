package com.yuta0124.wantedlyapp.buildlogic.primitive

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidRoomPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.devtools.ksp")
            }

            dependencies {
                implementation(libs.library("room"))
                implementation(libs.library("room-runtime"))
                ksp(libs.library("room-compiler"))
            }
        }
    }
}