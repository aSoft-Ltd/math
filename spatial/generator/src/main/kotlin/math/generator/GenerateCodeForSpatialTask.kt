package math.generator

import org.gradle.api.tasks.TaskAction
import java.io.File

open class GenerateCodeForSpatialTask : GenerateCodeAbstractTask() {

    @TaskAction
    fun doGenerate() {
        generateCommonCode()
        generateSpatialSpecificCode(outPutDir)
    }

    fun generateSpatialSpecificCode(outputDir: File) {
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

        generateImmutableDistanceOperations(params, ext.interfaces, ext.sPackage).forEach { file ->
            File(subDir, file.name).apply {
                createNewFile()
                writeText(file.content);
            }
        }
    }
}