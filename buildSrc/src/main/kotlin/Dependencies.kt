interface Dependency {
    val values: List<String>
}

object BuildPlugins {
    private const val androidGradlePluginVersion = "4.1.2"
    const val androidGradlePlugin = "com.android.tools.build:gradle:${androidGradlePluginVersion}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.version}"
}

object Kotlin : Dependency {
    const val version = "1.4.30"
    private const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${version}"

    override val values = listOf(kotlinStdlib)
}

object AndroidX : Dependency {
    private const val androidxKtx = "androidx.core:core-ktx:1.0.0"
    private const val androidCompat = "androidx.appcompat:appcompat:1.2.0"
    private const val material = "com.google.android.material:material:1.3.0"
    private const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.4"

    override val values = listOf(androidxKtx, androidCompat, material, constraintLayout)
}

fun using(dependency: Dependency, import: (String) -> Unit) {
    dependency.values.forEach { import.invoke(it) }
}

fun using(dependency: String, import: (String) -> Unit) {
    import.invoke(dependency)
}
