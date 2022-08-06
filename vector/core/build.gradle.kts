plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("io.codearte.nexus-staging")
    id("math.generator.spatial")
    signing
}

generator {
    immutable("Vec3", "MutableVec3", true)
    immutable("Vec2", "MutableVec2", false)

    mutable("MutableVec3", "MutableVec3", true)
    mutable("MutableVec2", "MutableVec2", false)
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
                api(projects.mathSpatialCore)
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
    description = "A multiplatform math library for dealing with vectors"
)