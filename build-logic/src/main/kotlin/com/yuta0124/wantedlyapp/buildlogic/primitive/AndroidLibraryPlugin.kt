package com.yuta0124.wantedlyapp.buildlogic.primitive

import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
            }
            androidLibrary {
                setupAndroid()
            }
        }
    }
}