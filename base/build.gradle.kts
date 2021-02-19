plugins {
    id("application-module-plugin")
    id("kotlin-android")
}
dependencies {
    using(Kotlin) { api(it) }
    using(AndroidX) { api(it) }
}
