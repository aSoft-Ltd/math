package math.generator

fun generateImmutableBinaryOperations(
    params: List<NumberType>,
    defs: List<SpacialDef>,
    opName: String,
    opSymbol: String,
): List<SourceFile> {
    val srcFiles = mutableListOf<SourceFile>()
    for (def in defs) {

        val code = buildString {
            appendLine(
                """
                    @file:Suppress("unused", "FunctionName")
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