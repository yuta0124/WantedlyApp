package com.yuta0124.wantedlyapp.buildlogic.primitive

import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.detektPlugins(
    artifact: MinimalExternalModuleDependency,
) {
    add("detektPlugins", artifact)
}