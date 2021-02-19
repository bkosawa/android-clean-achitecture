package com.bkosawa.gradle.extension

import BuildVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType

internal fun Project.applyKotlinConfiguration() {
    tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class).all {
        kotlinOptions {
            jvmTarget = BuildVersion.javaVersion
        }
    }
}