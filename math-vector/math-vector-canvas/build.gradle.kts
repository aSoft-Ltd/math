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
                api(asoft("theme-css", vers.asoft.theme))
                api(project(":math-vector-core"))
                api(project(":math-point-core"))
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
    description = "A multiplatform math library for dealing with vectors"
)