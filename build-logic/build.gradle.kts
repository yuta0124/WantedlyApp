import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

plugins {
    `kotlin-dsl`
}

group = "com.yuta0124.wantedlyapp.buildlogic"

tasks.withType<KotlinJvmCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.bundles.plugins)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = libs.plugins.wantedlyapp.android.gradle.plugin.get().pluginId
            implementationClass =
                "com.yuta0124.wantedlyapp.buildlogic.primitive.AndroidApplicationPlugin"
        }
        register("androidLibrary") {
            id = libs.plugins.wantedlyapp.android.library.plugin.get().pluginId
            implementationClass =
                "com.yuta0124.wantedlyapp.buildlogic.primitive.AndroidLibraryPlugin"
        }
        register("androidKotlin") {
            id = libs.plugins.wantedlyapp.android.kotlin.plugin.get().pluginId
            implementationClass =
                "com.yuta0124.wantedlyapp.buildlogic.primitive.AndroidKotlinPlugin"
        }
        register("androidCompose") {
            id = libs.plugins.wantedlyapp.android.compose.plugin.get().pluginId
            implementationClass =
                "com.yuta0124.wantedlyapp.buildlogic.primitive.AndroidComposePlugin"
        }
        register("detekt") {
            id = libs.plugins.wantedlyapp.detekt.get().pluginId
            implementationClass = "com.yuta0124.wantedlyapp.buildlogic.primitive.DetektPlugin"
        }
        register("androidHilt") {
            id = libs.plugins.wantedlyapp.hilt.plugin.get().pluginId
            implementationClass = "com.yuta0124.wantedlyapp.buildlogic.primitive.AndroidHiltPlugin"
        }
        register("androidRoom") {
            id = libs.plugins.wantedlyapp.room.plugin.get().pluginId
            implementationClass = "com.yuta0124.wantedlyapp.buildlogic.primitive.AndroidRoomPlugin"
        }
    }
}

