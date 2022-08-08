package math.generator

fun generateExternalConvertors(
    params: List<NumberType>, ifaces: SpatialInterfaces, subpackage: String
): List<SourceFile> {
    val srcFiles = mutableListOf<SourceFile>()
    for (from in listOf("XY", "XYZ")) {
        val code = buildString {
            appendLine(
                """
                    @file:Suppress("unused", "FunctionName")
                    package math.$subpackage
                    
                    import math.*
                    import kotlin.jvm.JvmOverloads
                    import kotlin.jvm.JvmName
                    
                    
                """.trimIndent()
            )
            for (def in ifaces.toAllDefs()) {
                appendLine("""@JvmName("from${from}To${def.iFace}")""")
                append("inline fun <N> $from<N>.to${def.iFace}(")
                if (from.contains("X")) append("x: N = this.x,")
                if (from.contains("Y")) append("y: N = this.y,")
                if (def.hasZ) {
                    if (from.contains("Z")) {
                        append("z: N = this.z")
                    } else {
                        append("z: N")
                    }
                    appendLine(") = ${def.iFace}(x, y, z)")
                } else {
                    appendLine(") = ${def.iFace}(x, y)")
                }
                appendLine()
            }
        }
        srcFiles.add(SourceFile("${from}ConvertorUtils.kt", code))
    }
    return srcFiles
}