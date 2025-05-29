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
            implementationClass = "com.yuta0124.wantedlyapp.buildlogic.primitive.AndroidApplicationPlugin"
        }
        register("detekt") {
            id = libs.plugins.wantedlyapp.detekt.get().pluginId
            implementationClass = "com.yuta0124.wantedlyapp.buildlogic.primitive.DetektPlugin"
        }
    }
}

