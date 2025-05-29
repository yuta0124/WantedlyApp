package com.yuta0124.wantedlyapp.buildlogic.primitive

import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import io.gitlab.arturbosch.detekt.report.ReportMergeTask
import org.gradle.api.Project
import org.gradle.api.tasks.TaskProvider
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.withType

fun Project.setupDetekt(extension: DetektExtension) {
    extension.apply {
        // parallel processing
        parallel = true
        // detekt configuration file
        config.setFrom("${project.rootDir}/config/detekt/detekt.yml")
        // baseline configuration file
        baseline = file("${project.rootDir}/config/detekt/baseline.xml")
        // apply your own configuration file on top of the default settings
        buildUponDefaultConfig = true
        // do not let them fail when there is a rule violation
        ignoreFailures = false
        // attempt to automatically correct rule violations
        autoCorrect = true
    }

    /** https://detekt.dev/docs/introduction/reporting#kotlin-dsl-1 */
    val reportMerge = if (!rootProject.tasks.names.contains("reportMerge")) {
        rootProject.tasks.register("reportMerge", ReportMergeTask::class) {
            output.set(rootProject.layout.buildDirectory.file("reports/detekt/merge.xml"))
        }
    } else {
        rootProject.tasks.named("reportMerge") as TaskProvider<ReportMergeTask>
    }

    plugins.withType<io.gitlab.arturbosch.detekt.DetektPlugin> {
        tasks.withType<io.gitlab.arturbosch.detekt.Detekt> detekt@{
            finalizedBy(reportMerge)

            source = project.files("./").asFileTree

            include("**/*.kt")
            include("**/*.kts")
            exclude("**/resources/**")
            exclude("**/build/**")


            reportMerge.configure {
                input.from(this@detekt.xmlReportFile) // or .sarifReportFile
            }
        }
    }
}
