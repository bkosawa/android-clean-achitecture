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

object Dagger : Dependency {
    internal const val daggerLibrary = "2.32"
    private const val dagger = "com.google.dagger:dagger:${daggerLibrary}"
    private const val daggerAndroidSupport = "com.google.dagger:dagger-android-support:${daggerLibrary}"

    override val values = listOf(dagger, daggerAndroidSupport)
}

object DaggerCompiler : Dependency {
    private const val dagger = "com.google.dagger:dagger-compiler:${Dagger.daggerLibrary}"
    private const val daggerAndroidProcessor = "com.google.dagger:dagger-android-processor:${Dagger.daggerLibrary}"

    override val values = listOf(dagger, daggerAndroidProcessor)
}

object AnnotationsDependencies {
    private const val javaxAnnotationLibrary = "1.0"
    private const val javaxAnnotation = "javax.annotation:jsr250-api:$javaxAnnotationLibrary"

    val values = listOf(javaxAnnotation)
}

fun using(dependency: Dependency, import: (String) -> Unit) {
    dependency.values.forEach { import.invoke(it) }
}

fun using(dependency: String, import: (String) -> Unit) {
    import.invoke(dependency)
}
