plugins {
    kotlin("js")
    id("tz.co.asoft.applikation")
}

applikation {
    debug()
    release()
}

kotlin {
    js(IR) { application() }
    sourceSets {
        val main by getting {
            dependencies {
                api(project(":math-coordinates-cartesian-canvas"))
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