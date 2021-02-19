import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        register("application-module-plugin") {
            id = "application-module-plugin"
            implementationClass = "com.bkosawa.gradle.ApplicationModulePlugin"
        }
        register("library-module-plugin") {
            id = "library-module-plugin"
            implementationClass = "com.bkosawa.gradle.LibraryModulePlugin"
        }
    }
}

repositories {
    jcenter()
    google()
}

dependencies {
    compileOnly(gradleApi())

    implementation("com.android.tools.build:gradle:4.1.2")
    implementation(kotlin("gradle-plugin", "1.4.30"))
}