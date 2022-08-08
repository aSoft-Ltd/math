package math.generator

import org.gradle.api.tasks.TaskAction
import java.io.File

open class GenerateCodeForVectorTask : GenerateCodeAbstractTask() {

    @TaskAction
    fun doGenerate() {
        generateCommonCode()
        generateVectorSpecificCode()
    }

    fun generateVectorSpecificCode() {
        mutableListOf<SourceFile>().apply {
            addAll(generateImmutableBinaryOperations(params, ext.interfaces, ext.sPackage, "plus", "+"))
            addAll(generateImmutableBinaryOperations(params, ext.interfaces, ext.sPackage, "minus", "-"))
        }.forEach { file ->
            File(subDir, file.name).apply {
                createNewFile()
                writeText(file.content);
            }
        }

        mutableListOf<SourceFile>().apply {
            addAll(generateMutableBinaryOperations(params, ext.interfaces, ext.sPackage, "plus", "+"))
            addAll(generateMutableBinaryOperations(params, ext.interfaces, ext.sPackage, "minus", "-"))
        }.forEach { file ->
            File(subDir, file.name).apply {
                createNewFile()
                writeText(file.content);
            }
        }

        generateImmutableDotProduct(params, ext.interfaces).forEach { file ->
            File(subDir, file.name).apply {
                createNewFile()
                writeText(file.content);
            }
        }

        generateImmutableCrossProduct(params, ext.interfaces).forEach { file ->
            File(subDir, file.name).apply {
                createNewFile()
                writeText(file.content);
            }
        }

        generateImmutableMagnitudeOperations(params, ext.interfaces, ext.sPackage).forEach { file ->
            File(subDir, file.name).apply {
                createNewFile()
                writeText(file.content);
            }
        }

        generateExternalConvertors(params, ext.interfaces, ext.sPackage).forEach { file ->
            File(subDir, file.name).apply {
                createNewFile()
                writeText(file.content);
            }
        }

        generateImmutableNormalizeOperations(params, ext.interfaces).forEach { file ->
            File(subDir, file.name).apply {
                createNewFile()
                writeText(file.content);
            }
        }

        generateMutableNormalizeOperations(params, ext.interfaces).forEach { file ->
            File(subDir, file.name).apply {
                createNewFile()
                writeText(file.content);
            }
        }
    }
}