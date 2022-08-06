package math.generator

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import org.gradle.kotlin.dsl.getByType
import java.io.File

open class GenerateCodeForPointTask : DefaultTask() {
    private val ext get() = project.extensions.getByType<SpacialGeneratorExtension>()

    @OutputDirectory
    val outPutDir = File("generated/src/commonMain/kotlin")


    @TaskAction
    fun doGenerate() {
        generateCode(project.buildDir, outPutDir.path, ext.immutableDefs, ext.mutableDefs)
    }

    fun generateCode(
        buildDir: File,
        path: String,
        immutableDefs: List<SpatialDef>,
        mutableDefs: List<SpatialDef>
    ) {
        val mathDir = File(buildDir, "$path/math")
        val params = NumberType.values().toList()

        val defs = mutableDefs + immutableDefs

        if (!mathDir.exists()) {
            mathDir.mkdirs()
        }

        generateConstructors(params, ext.interfaces).forEach { file ->
            File(mathDir, file.name).apply {
                createNewFile()
                writeText(file.content);
            }
        }

        val subDir = File(mathDir, ext.sPackage).also { it.mkdir() }

        generateConvertors(params, ext.interfaces, ext.sPackage).forEach { file ->
            File(subDir, file.name).apply {
                createNewFile()
                writeText(file.content);
            }
        }

        mutableListOf<SourceFile>().apply {
            addAll(generateImmutableBinaryOperations(params, immutableDefs, "plus", "+"))
            addAll(generateImmutableBinaryOperations(params, immutableDefs, "minus", "-"))
        }.forEach { file ->
            File(subDir, file.name).apply {
                createNewFile()
                writeText(file.content);
            }
        }

        mutableListOf<SourceFile>().apply {
            addAll(generateMutableBinaryOperations(params, mutableDefs, "plus", "+"))
            addAll(generateMutableBinaryOperations(params, mutableDefs, "minus", "-"))
        }.forEach { file ->
            File(subDir, file.name).apply {
                createNewFile()
                writeText(file.content);
            }
        }
    }
}