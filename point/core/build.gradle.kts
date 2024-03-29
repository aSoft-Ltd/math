plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("io.codearte.nexus-staging")
    id("math.generator.spatial")
    signing
}

generator {
    subpackage("point")
    interfaces(xy = "Point2", xyz = "Point3")
}

kotlin {
    jvm { library() }
    js(IR) { library() }
//    val nativeTargets = nativeTargets()
    val nativeTargets = linuxTargets(true)
    targets.configureEach {
        compilations.all {
            compileKotlinTask.dependsOn("generateCode")
        }
    }
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
    description = "A multiplatform math library for dealing with points in space"
)