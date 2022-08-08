package math.generator

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.OutputDirectory
import org.gradle.kotlin.dsl.getByType
import java.io.File

abstract class GenerateCodeAbstractTask : DefaultTask() {
    @get:Internal
    internal val ext get() = project.extensions.getByType<SpatialGeneratorExtension>()

    @OutputDirectory
    val outPutDir = File(project.buildDir, "generated/src/commonMain/kotlin")

    @get:OutputDirectory
    protected val mathDir
        get() = File(outPutDir, "math").also {
            if (!it.exists()) it.mkdirs()
        }

    @get:OutputDirectory
    protected val internalDir
        get() = File(mathDir, "internal").also {
            if (!it.exists()) it.mkdirs()
        }

    @get:Input
    protected val params = NumberType.values().toList()

    @get:OutputDirectory
    protected val subDir
        get() = File(mathDir, ext.sPackage).also {
            if (!it.exists()) it.mkdirs()
        }

    fun generateCommonCode() {
        val internalDir = File(mathDir, "internal").also { it.mkdir() }
        generateImplementations(params, ext.interfaces).forEach { file ->
            File(internalDir, file.name).apply {
                createNewFile()
                writeText(file.content);
            }
        }

        generateConstructors(params, ext.interfaces).forEach { file ->
            File(mathDir, file.name).apply {
                createNewFile()
                writeText(file.content);
            }
        }

        generateInternalConvertors(params, ext.interfaces, ext.sPackage).forEach { file ->
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

        generateImmutableScalarOperations(params, ext.interfaces, ext.sPackage).forEach { file ->
            File(subDir, file.name).apply {
                createNewFile()
                writeText(file.content);
            }
        }

        generateMutableScalarOperations(params, ext.interfaces, ext.sPackage).forEach { file ->
            File(subDir, file.name).apply {
                createNewFile()
                writeText(file.content);
            }
        }

        generateImmutableUnaryMinusOperations(params, ext.interfaces, ext.sPackage).forEach { file ->
            File(subDir, file.name).apply {
                createNewFile()
                writeText(file.content);
            }
        }

        generateMutableUnaryMinusOperations(params, ext.interfaces, ext.sPackage).forEach { file ->
            File(subDir, file.name).apply {
                createNewFile()
                writeText(file.content);
            }
        }
    }
}