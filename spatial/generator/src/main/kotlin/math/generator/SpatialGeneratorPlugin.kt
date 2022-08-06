package math.generator

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.InvalidPluginException

open class SpatialGeneratorPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.extensions.create("generator", SpacialGeneratorExtension::class.java)
        val clazz = when {
            target.name.contains("spatial") -> GenerateCodeForSpatialTask::class.java
            target.name.contains("point") -> GenerateCodeForPointTask::class.java
            target.name.contains("vector") -> GenerateCodeForVectorTask::class.java
            else -> throw InvalidPluginException("Plugin can't be applied to project ${target.name}")
        }
        target.tasks.create("generateCode", clazz)
    }
}