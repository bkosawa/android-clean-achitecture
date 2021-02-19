plugins {
    id("library-module-plugin")
}

dependencies {
    using(Kotlin) { implementation(it) }
    using(Network) { implementation(it) }
}