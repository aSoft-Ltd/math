plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("io.codearte.nexus-staging")
    id("math.generator.spatial")
    signing
}

generator {
    subpackage("spatial")
    interfaces(xy = "XY", xyz = "XYZ")
}

kotlin {
    jvm { library() }
    js(IR) { library() }
//    val nativeTargets = nativeTargets()
    val nativeTargets = linuxTargets(true)
    sourceSets {
        val commonMain by getting {
            kotlin.srcDir("build/generated/src/commonMain/kotlin")
            dependencies {
                api(projects.mathCore)
            }
        }
        val commonTest by getting {
            dependencies {
                api(projects.expectCore)
            }
        }
    }
}

aSoftOSSLibrary(
    version = asoft.versions.root.get(),
    description = "A multiplatform math library for dealing with points in space"
)