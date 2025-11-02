package com.yuta0124.wantedlyapp.buildlogic.primitive

import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.TestedExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import dev.detekt.gradle.Detekt
import dev.detekt.gradle.extensions.DetektExtension
import dev.detekt.gradle.plugin.DetektPlugin
import dev.detekt.gradle.report.ReportMergeTask
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.tasks.TaskProvider
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.withType

fun Project.androidApplication(action: BaseAppModuleExtension.() -> Unit) {
    extensions.configure(action)
}

fun Project.androidLibrary(action: LibraryExtension.() -> Unit) {
    extensions.configure(action)
}

fun Project.android(action: TestedExtension.() -> Unit) {
    extensions.configure(action)
}

fun Project.setupAndroid() {
    android {
        namespace?.let {
            this.namespace = it
        }
        compileSdkVersion(36)

        defaultConfig {
            minSdk = 24
            targetSdk = 36
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

            vectorDrawables {
                useSupportLibrary = true
            }
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }
    }
}

fun Project.setupDetekt() {
    plugins.withType<DetektPlugin> {
        extensions.configure<DetektExtension> {
            parallel.set(true)
            // detekt configuration file
            config.setFrom("${project.rootDir}/config/detekt/detekt.yml")
            // baseline configuration file
            baseline.set(file("${project.rootDir}/config/detekt/baseline.xml"))
            // apply your own configuration file on top of the default settings
            buildUponDefaultConfig.set(true)
            // do not let them fail when there is a rule violation
            ignoreFailures.set(false)
            // attempt to automatically correct rule violations
            autoCorrect.set(true)

        }

        /** https://detekt.dev/docs/introduction/reporting#kotlin-dsl-1 */
    }
    tasks.withType<Detekt>().configureEach {
        val reportMerge = if (!rootProject.tasks.names.contains("reportMerge")) {
            rootProject.tasks.register("reportMerge", ReportMergeTask::class) {
                output.set(rootProject.layout.buildDirectory.file("reports/detekt/merge.xml"))
            }
        } else {
            rootProject.tasks.named("reportMerge") as TaskProvider<ReportMergeTask>
        }

        finalizedBy(reportMerge)

        reportMerge.configure {
            input.from(reports.checkstyle.outputLocation)
        }
    }
}
