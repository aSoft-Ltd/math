pluginManagement {
    repositories {
        google()
        jcenter()
        gradlePluginPortal()
        mavenCentral()
    }

    resolutionStrategy {
        eachPlugin {
            if (requested.id.namespace == "com.android") {
                useModule("com.android.tools.build:gradle:${requested.version}")
            }
        }
    }
}

rootProject.name = "math"

include(":math-core")

include(":math-point-core")
project(":math-point-core").projectDir = File("math-point/math-point-core")

include(":math-point-canvas")
project(":math-point-canvas").projectDir = File("math-point/math-point-canvas")

include(":math-vector-core")
project(":math-vector-core").projectDir = File("math-vector/math-vector-core")

include(":math-vector-canvas")
project(":math-vector-canvas").projectDir = File("math-vector/math-vector-canvas")

include(":math-coordinates-cartesian-core")
project(":math-coordinates-cartesian-core").projectDir = File("math-coordinates/math-coordinates-cartesian/math-coordinates-cartesian-core")
include(":math-coordinates-cartesian-canvas")
project(":math-coordinates-cartesian-canvas").projectDir = File("math-coordinates/math-coordinates-cartesian/math-coordinates-cartesian-canvas")
include(":math-coordinates-cartesian-sample")
project(":math-coordinates-cartesian-sample").projectDir = File("math-coordinates/math-coordinates-cartesian/math-coordinates-cartesian-sample")
