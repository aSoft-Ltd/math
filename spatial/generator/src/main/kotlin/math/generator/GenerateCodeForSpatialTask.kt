package math.generator

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import org.gradle.kotlin.dsl.getByType
import java.io.File

open class GenerateCodeForSpatialTask : DefaultTask() {
    private val ext get() = project.extensions.getByType<SpatialGeneratorExtension>()

    @OutputDirectory
    val outPutDir = File(project.buildDir,"generated/src/commonMain/kotlin")


    @TaskAction
    fun doGenerate() {
        generateCode(outPutDir, ext.immutableDefs, ext.mutableDefs)
    }

    fun generateCode(
        outputDir: File, immutableDefs: List<SpatialDef>, mutableDefs: List<SpatialDef>
    ) {
        val mathDir = File(outputDir, "math")
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

        generateCopyUtils(ext.interfaces, ext.sPackage).forEach { file ->
            File(subDir, file.name).apply {
                createNewFile()
                writeText(file.content);
            }
        }

        generateImmutableScalarOperations(params,ext.interfaces,ext.sPackage).forEach { file ->
            File(subDir, file.name).apply {
                createNewFile()
                writeText(file.content);
            }
        }

        generateMutableScalarOperations(params,ext.interfaces,ext.sPackage).forEach { file ->
            File(subDir, file.name).apply {
                createNewFile()
                writeText(file.content);
            }
        }

        mutableListOf<SourceFile>().apply {
            addAll(generateImmutableBinaryOperations(params, ext.interfaces, ext.sPackage, "plus", "+"))
            addAll(generateImmutableBinaryOperations(params, ext.interfaces, ext.sPackage, "minus", "-"))
            addAll(generateImmutableBinaryOperations(params, ext.interfaces, ext.sPackage, "times", "*"))
            addAll(generateImmutableBinaryOperations(params, ext.interfaces, ext.sPackage, "div", "/"))
        }.forEach { file ->
            File(subDir, file.name).apply {
                createNewFile()
                writeText(file.content);
            }
        }

        mutableListOf<SourceFile>().apply {
            addAll(generateMutableBinaryOperations(params, ext.interfaces, ext.sPackage, "plus", "+"))
            addAll(generateMutableBinaryOperations(params, ext.interfaces, ext.sPackage, "minus", "-"))
            addAll(generateMutableBinaryOperations(params, ext.interfaces, ext.sPackage, "times", "*"))
            addAll(generateMutableBinaryOperations(params, ext.interfaces, ext.sPackage, "div", "/"))
        }.forEach { file ->
            File(subDir, file.name).apply {
                createNewFile()
                writeText(file.content);
            }
        }

        generateDistanceOperations(params, immutableDefs).forEach { file ->
            File(subDir, file.name).apply {
                createNewFile()
                writeText(file.content);
            }
        }
    }
}