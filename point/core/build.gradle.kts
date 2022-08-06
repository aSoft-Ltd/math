plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("io.codearte.nexus-staging")
    signing
}

val path = "generated/src/commonMain/kotlin"

kotlin {
    jvm { library() }
    js(IR) { library() }
//    val nativeTargets = nativeTargets()
    val nativeTargets = linuxTargets(true)
    sourceSets {
        val commonMain by getting {
            generate(path)
            kotlin.srcDir("build/$path")
            dependencies {
                api(projects.mathCore)
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
    version = asoft.versions.root.get(), description = "A multiplatform math library for dealing with points in space"
)

val generateBuilders by tasks.creating {
    doFirst { generate(path) }
}

enum class NumberType(val label: String, val default: String) {
    Short("Short", "0"), Byte("Byte", "0"), Int("Int", "0"), Long("Long", "0"), Float("Float", "0f"), Double("Double", "0.0");

    fun canHold(type: NumberType) = when (this) {
        Short -> false
        Byte -> false
        Int -> listOf(Short, Byte, Int).contains(type)
        Long -> listOf(Short, Byte, Int, Long).contains(type)
        Float -> type != Double
        Double -> true
    }
}

class Param(val type: String, val default: String)

class PointDef(
    val iFace: String,
    val imp: String,
    val hasZ: Boolean,
) {
    val hasX = true
    val hasY = true
}

fun generate(path: String) {
    val mathDir = File(buildDir, "$path/math")
    val params = NumberType.values().toList()

    val immutableDefs = listOf(
        PointDef("Point3", "MutablePoint3", true),
        PointDef("Point2", "MutablePoint2", false),
    )

    val mutableDefs = listOf(
        PointDef("MutablePoint3", "MutablePoint3", true), PointDef("MutablePoint2", "MutablePoint2", false)
    )
    val defs = mutableDefs + immutableDefs

    if (!mathDir.exists()) {
        mathDir.mkdirs()
    }

    generateBuilders(params, defs).forEach { file ->
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

    generateAllImmutableBinaryOperation(params, immutableDefs).forEach { file ->
        File(mathPointDir, file.name).apply {
            createNewFile()
            writeText(file.content);
        }
    }

    generateAllMutableBinaryOperation(params, mutableDefs).forEach { file ->
        File(mathPointDir, file.name).apply {
            createNewFile()
            writeText(file.content);
        }
    }

    generateSquareDistanceOperations(params, immutableDefs).forEach { file ->
        File(mathPointDir, file.name).apply {
            createNewFile()
            writeText(file.content);
        }
    }
}

class SourceFile(val name: String, val content: String)

fun generateBuilders(params: List<NumberType>, defs: List<PointDef>): List<SourceFile> {
    val srcFiles = mutableListOf<SourceFile>()
    for (def in defs) {
        val code = buildString {
            appendLine(
                """
                    @file:Suppress("unused", "FunctionName")
                    package math
                    
                    import math.internal.${def.imp}Impl
                    import kotlin.jvm.JvmOverloads
                    import kotlin.jvm.JvmName
                    
                    
                """.trimIndent()
            )
            for (param in params) {
                appendLine("@JvmOverloads")
                appendLine("""@JvmName("${def.iFace}Of${param.label}")""")
                appendLine("fun ${def.iFace}(")
                if (def.hasX) appendLine("\tx: ${param.label} = ${param.default},")
                if (def.hasY) appendLine("\ty: ${param.label} = ${param.default},")
                if (def.hasZ) appendLine("\tz: ${param.label} = ${param.default},")
                append("): ${def.iFace}<${param.label}> = ")
                appendLine("${def.imp}Impl(x, y${if (def.hasZ) ", z" else ""})\n")
            }
        }
        srcFiles.add(SourceFile("${def.iFace}Builders.kt", code))
    }
    return srcFiles
}

fun generateCopyUtils(params: List<NumberType>, defs: List<PointDef>): List<SourceFile> {
    val srcFiles = mutableListOf<SourceFile>()
    for (def in defs) {
        val code = buildString {
            appendLine(
                """
                    package math.point
                    
                    import math.${def.iFace}
                    
                """.trimIndent()
            )
            for (param in params) {
                appendLine("inline fun ${def.iFace}<${param.label}>.copy(")
                if (def.hasX) appendLine("\tx: ${param.label} = this.x,")
                if (def.hasY) appendLine("\ty: ${param.label} = this.y,")
                if (def.hasZ) appendLine("\tz: ${param.label} = this.z,")
                appendLine(") = ${def.iFace}(x, y${if (def.hasZ) ", z" else ""})\n")
            }
        }
        srcFiles.add(SourceFile("${def.iFace}CopyUtils.kt", code))
    }
    return srcFiles
}

fun generateAllImmutableBinaryOperation(
    params: List<NumberType>, defs: List<PointDef>
): List<SourceFile> = mutableListOf<SourceFile>().apply {
    addAll(generateImutableBinaryOperations(params, defs, "plus", "+"))
    addAll(generateImutableBinaryOperations(params, defs, "minus", "-"))
    addAll(generateImutableBinaryOperations(params, defs, "times", "*"))
    addAll(generateImutableBinaryOperations(params, defs, "div", "/"))
}

fun generateAllMutableBinaryOperation(
    params: List<NumberType>, defs: List<PointDef>
): List<SourceFile> = mutableListOf<SourceFile>().apply {
    addAll(generateMutableBinaryOperations(params, defs, "plus", "+"))
    addAll(generateMutableBinaryOperations(params, defs, "minus", "-"))
    addAll(generateMutableBinaryOperations(params, defs, "times", "*"))
    addAll(generateMutableBinaryOperations(params, defs, "div", "/"))
}

fun generateImutableBinaryOperations(
    params: List<NumberType>,
    defs: List<PointDef>,
    opName: String,
    opSymbol: String,
): List<SourceFile> {
    val srcFiles = mutableListOf<SourceFile>()
    for (def in defs) {

        val code = buildString {
            appendLine(
                """
                    package math.point
                    
                    import math.${def.iFace}
                    import kotlin.jvm.JvmName
                    
                """.trimIndent()
            )
            for (left in params) {
                for (right in params) {
                    appendLine("""@JvmName("${left.label}${opName.capitalize()}${right.label}")""")
                    append("inline operator fun ${def.iFace}<${left.label}>.")
                    append("$opName(point: ${def.iFace}<${right.label}>) = ${def.iFace}(")
                    if (def.hasX) append("this.x $opSymbol point.x,")
                    if (def.hasY) append("this.y $opSymbol point.y,")
                    if (def.hasZ) append("this.z $opSymbol point.z")
                    appendLine(")\n")

                    appendLine("""@JvmName("${left.label}${opName.capitalize()}${right.label}")""")
                    append("inline operator fun ${def.iFace}<${left.label}>.")
                    append("$opName(other: ${right.label}) = ${def.iFace}(")
                    if (def.hasX) append("this.x $opSymbol other,")
                    if (def.hasY) append("this.y $opSymbol other,")
                    if (def.hasZ) append("this.z $opSymbol other")
                    appendLine(")\n")

                    appendLine("""@JvmName("${left.label}${opName.capitalize()}${right.label}")""")
                    append("inline operator fun ${right.label}.")
                    append("$opName(point: ${def.iFace}<${left.label}>) = ${def.iFace}(")
                    if (def.hasX) append("this $opSymbol point.x,")
                    if (def.hasY) append("this $opSymbol point.y,")
                    if (def.hasZ) append("this $opSymbol point.z")
                    appendLine(")\n")
                }
            }
        }
        srcFiles.add(SourceFile("${def.iFace}${opName.capitalize()}Utils.kt", code))
    }
    return srcFiles
}

fun generateMutableBinaryOperations(
    params: List<NumberType>,
    defs: List<PointDef>,
    opName: String,
    opSymbol: String,
): List<SourceFile> {
    val srcFiles = mutableListOf<SourceFile>()
    for (def in defs) {

        val code = buildString {
            appendLine(
                """
                    package math.point
                    
                    import math.${def.iFace}
                    import kotlin.jvm.JvmName
                    
                    
                """.trimIndent()
            )
            for (left in params) {
                for (right in params) {
                    if (!left.canHold(right)) continue
                    appendLine("""@JvmName("${left.label}${opName.capitalize()}${right.label}")""")
                    append("inline operator fun ${def.iFace}<${left.label}>.")
                    appendLine("$opName(point: ${def.iFace}<${right.label}>) {")
                    if (def.hasX) appendLine("\tx $opSymbol= point.x")
                    if (def.hasY) appendLine("\ty $opSymbol= point.y")
                    if (def.hasZ) appendLine("\tz $opSymbol= point.z")
                    appendLine("}\n")

                    appendLine("""@JvmName("${left.label}${opName.capitalize()}${right.label}")""")
                    append("inline operator fun ${def.iFace}<${left.label}>.")
                    appendLine("$opName(other: ${right.label}) {")
                    if (def.hasX) appendLine("\tx $opSymbol= other")
                    if (def.hasY) appendLine("\ty $opSymbol= other")
                    if (def.hasZ) appendLine("\tz $opSymbol= other")
                    appendLine("}\n")

                    appendLine("""@JvmName("${left.label}${opName.capitalize()}${right.label}")""")
                    append("inline operator fun ${right.label}.")
                    appendLine("$opName(point: ${def.iFace}<${left.label}>) {")
                    if (def.hasX) appendLine("\tpoint.x = this $opSymbol point.x")
                    if (def.hasY) appendLine("\tpoint.y = this $opSymbol point.y")
                    if (def.hasZ) appendLine("\tpoint.z = this $opSymbol point.z")
                    appendLine("}\n")
                }
            }
        }
        srcFiles.add(SourceFile("${def.iFace}${opName.capitalize()}Utils.kt", code))
    }
    return srcFiles
}

fun generateSquareDistanceOperations(
    params: List<NumberType>, defs: List<PointDef>
): List<SourceFile> {
    val srcFiles = mutableListOf<SourceFile>()
    for (def in defs) {

        val code = buildString {
            appendLine(
                """
                    package math.point
                    
                    import math.${def.iFace}
                    import math.sqrt
                    import kotlin.jvm.JvmName
                    
                    
                """.trimIndent()
            )
            for (left in params) {
                for (right in params) {
                    appendLine("""@JvmName("${left.label}PointSquareDistanceTo${right.label}Point")""")
                    append("inline fun ${def.iFace}<${left.label}>.")
                    append("squareDistanceTo(point: ${def.iFace}<${right.label}>) =")
                    if (def.hasX) append("  ((x - point.x) * (x - point.x)) ")
                    if (def.hasY) append("+ ((y - point.y) * (y - point.y)) ")
                    if (def.hasZ) append("+ ((z - point.z) * (z - point.z)) ")
                    appendLine("\n")

                    appendLine("""@JvmName("${left.label}PointDistanceTo${right.label}Point")""")
                    append("inline fun ${def.iFace}<${left.label}>.")
                    appendLine("distanceTo(point: ${def.iFace}<${right.label}>) = sqrt(squareDistanceTo(point))\n")
                }
            }
        }
        srcFiles.add(SourceFile("${def.iFace}DistanceUtils.kt", code))
    }
    return srcFiles
}