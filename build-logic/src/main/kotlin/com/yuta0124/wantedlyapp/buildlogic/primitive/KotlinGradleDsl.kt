package com.yuta0124.wantedlyapp.buildlogic.primitive

import com.android.build.gradle.TestedExtension
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

fun Project.kotlin(action: KotlinAndroidProjectExtension.() -> Unit) {
    extensions.configure(action)
}

fun TestedExtension.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
    (this as ExtensionAware).extensions.configure("kotlinOptions", block)
}
