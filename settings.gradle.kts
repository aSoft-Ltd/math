pluginManagement {
    enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }

    dependencyResolutionManagement {
        versionCatalogs {
            file("gradle/versions").listFiles().map {
                it.nameWithoutExtension to it.absolutePath
            }.forEach { (name, path) ->
                create(name) { from(files(path)) }
            }
        }
    }
}

fun includeRoot(name: String, path: String) {
    include(":$name")
    project(":$name").projectDir = File(path)
}

fun includeSubs(base: String, path: String = base, vararg subs: String) {
    subs.forEach {
        include(":$base-$it")
        project(":$base-$it").projectDir = File("$path/$it")
    }
}

val tmp = 1

rootProject.name = "asoft"

includeBuild("./spatial/generator")

// dependencies
includeSubs("functions", "../functions", "core")
includeSubs("expect", "../expect", "core")

// submodules
includeSubs("math", ".", "core")
includeSubs("math-spatial", "spatial", "core")
includeSubs("math-point", "point", "core")
includeSubs("math-vector", "vector", "core")