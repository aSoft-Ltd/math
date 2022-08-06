package math.generator

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import org.gradle.kotlin.dsl.getByType
import java.io.File

open class GenerateCodeForVectorTask : DefaultTask() {
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
        immutableDefs: List<SpacialDef>,
        mutableDefs: List<SpacialDef>
    ) {
        val mathDir = File(buildDir, "$path/math")
        val params = NumberType.values().toList()

        val defs = mutableDefs + immutableDefs

        if (!mathDir.exists()) {
            mathDir.mkdirs()
        }

        generateConstructors(params, defs).forEach { file ->
            File(mathDir, file.name).apply {
                createNewFile()
                writeText(file.content);
            }
        }

        val mathPointDir = File(mathDir, "point").also { it.mkdir() }
        generateCopyUtils(params, defs).forEach { file ->
            File(mathPointDir, file.name).apply {
                createNewFile()
                writeText(file.content);
            }
        }

        mutableListOf<SourceFile>().apply {
            addAll(generateImmutableBinaryOperations(params, immutableDefs, "plus", "+"))
            addAll(generateImmutableBinaryOperations(params, immutableDefs, "minus", "-"))
        }.forEach { file ->
            File(mathPointDir, file.name).apply {
                createNewFile()
                writeText(file.content);
            }
        }

        mutableListOf<SourceFile>().apply {
            addAll(generateMutableBinaryOperations(params, mutableDefs, "plus", "+"))
            addAll(generateMutableBinaryOperations(params, mutableDefs, "minus", "-"))
        }.forEach { file ->
            File(mathPointDir, file.name).apply {
                createNewFile()
                writeText(file.content);
            }
        }

        generateImmutableDotOperationsForVectors(params, immutableDefs).forEach { file ->
            File(mathPointDir, file.name).apply {
                createNewFile()
                writeText(file.content);
            }
        }
    }
}