package math.generator

import org.gradle.api.tasks.TaskAction
import java.io.File

open class GenerateCodeForPointTask : GenerateCodeAbstractTask() {

    @TaskAction
    fun doGenerate() {
        generateCommonCode()
        generatePointSpecificCode()
    }

    fun generatePointSpecificCode() {
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

        generateExternalConvertors(params, ext.interfaces, ext.sPackage).forEach { file ->
            File(subDir, file.name).apply {
                createNewFile()
                writeText(file.content);
            }
        }
    }
}