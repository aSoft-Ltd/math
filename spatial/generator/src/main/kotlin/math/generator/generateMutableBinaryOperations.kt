package math.generator

fun generateMutableBinaryOperations(
    params: List<NumberType>,
    defs: List<SpatialDef>,
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