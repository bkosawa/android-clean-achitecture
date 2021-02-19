listOf(
    ":base",
    ":framework:network"
).forEach {
    include(it)
}

rootProject.name = "Clean Architecture Android"