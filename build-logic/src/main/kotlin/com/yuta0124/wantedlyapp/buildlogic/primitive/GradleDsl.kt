package com.yuta0124.wantedlyapp.buildlogic.primitive

import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.implementation(
    artifact: MinimalExternalModuleDependency,
) {
    add("implementation", artifact)
}

fun DependencyHandlerScope.detektPlugins(
    artifact: MinimalExternalModuleDependency,
) {
    add("detektPlugins", artifact)
}