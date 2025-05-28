package com.yuta0124.wantedlyapp.buildlogic.primitive

import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import io.gitlab.arturbosch.detekt.report.ReportMergeTask
import org.gradle.api.Project
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
    val reportMerge = rootProject.tasks.withType(ReportMergeTask::class)
    tasks.withType<Detekt>().configureEach {
        reports {
            sarif.required.set(true)
        }
        finalizedBy(reportMerge)
    }
    reportMerge.configureEach {
        input.from(tasks.withType<Detekt>().map(Detekt::sarifReportFile))
    }
}
