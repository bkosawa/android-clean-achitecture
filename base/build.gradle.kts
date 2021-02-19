plugins {
    id("application-module-plugin")
    id("kotlin-android")
}
dependencies {
    implementation(project(":framework:network"))

    using(Kotlin) { api(it) }
    using(AndroidX) { api(it) }
    using(Dagger) { implementation(it) }
    using(DaggerCompiler) { kapt(it) }
    using(Network.retrofit) { implementation(it) }
}
