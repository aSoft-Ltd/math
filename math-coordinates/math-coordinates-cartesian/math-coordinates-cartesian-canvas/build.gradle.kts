plugins {
    kotlin("js")
    id("tz.co.asoft.library")
    id("io.codearte.nexus-staging")
    signing
}

kotlin {
    js(IR) { library() }
    sourceSets {
        val main by getting {
            dependencies {
                api(project(":math-coordinates-cartesian-core"))
                api(project(":math-vector-canvas"))
                api(project(":math-point-canvas"))
                api(asoft("theme-css", vers.asoft.theme))
            }
        }
        val test by getting {
            dependencies {
                api(asoft("test-core", vers.asoft.test))
                api(asoft("expect-core", vers.asoft.expect))
            }
        }
    }
}

aSoftOSSLibrary(
    version = vers.asoft.math,
    description = "A multiplatform math library providing mathematical utilities"
)