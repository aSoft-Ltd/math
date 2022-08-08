plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("io.codearte.nexus-staging")
    id("math.generator.spatial")
    signing
}

generator {
    subpackage("vector")
    interfaces(xy = "Vec2", xyz = "Vec3")
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
    description = "A multiplatform math library for dealing with vectors"
)