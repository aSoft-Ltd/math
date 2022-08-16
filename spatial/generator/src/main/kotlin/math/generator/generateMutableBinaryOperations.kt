package math.generator


fun generateMutableBinaryOperations(
    params: List<NumberType>,
    ifaces: SpatialInterfaces,
    subpackage: String,
    opName: String,
    opSymbol: String,
): List<SourceFile> {
    val srcFiles = mutableListOf<SourceFile>()

    for (def in ifaces.toMutableDefs()) {
        val code = buildString {
            appendLine(
                """
                    @file:Suppress("unused", "FunctionName")
                    package math.$subpackage
                    
                    import math.*
                    import kotlin.jvm.JvmName
                    
                """.trimIndent()
            )
            for (leftParam in params) {
                for (rightParam in params) {
                    if (!leftParam.canBeOperatedWith(rightParam)) continue
                    appendLine("""@JvmName("${def.iFace}${leftParam.label}${opName.capitalize()}XY${rightParam.label}")""")
                    append("inline operator fun ${def.iFace}<${leftParam.label}>.")
                    appendLine("${opName}Assign(other: XY<${rightParam.label}>) {")
                    if (def.hasX) appendLine("\tx $opSymbol= other.x")
                    if (def.hasY) appendLine("\ty $opSymbol= other.y")
                    if (def.hasZ) appendLine("\tz $opSymbol= ${rightParam.default}")
                    appendLine("}\n")

                    appendLine("""@JvmName("${def.iFace}${leftParam.label}${opName.capitalize()}XY${rightParam.label}")""")
                    append("inline operator fun ${def.iFace}<${leftParam.label}>.")
                    appendLine("${opName}Assign(other: XYZ<${rightParam.label}>) {")
                    if (def.hasX) appendLine("\tx $opSymbol= other.x")
                    if (def.hasY) appendLine("\ty $opSymbol= other.y")
                    if (def.hasZ) appendLine("\tz $opSymbol= other.y")
                    appendLine("}\n")

                    appendLine("""@JvmName("${def.iFace}${leftParam.label}${opName.capitalize()}${rightParam.label}")""")
                    append("inline operator fun ${def.iFace}<${leftParam.label}>.")
                    appendLine("${opName}Assign(other: ${rightParam.label}) {")
                    if (def.hasX) appendLine("\tx $opSymbol= other")
                    if (def.hasY) appendLine("\ty $opSymbol= other")
                    if (def.hasZ) appendLine("\tz $opSymbol= other")
                    appendLine("}\n")
                }
            }
        }
        srcFiles.add(SourceFile("${def.iFace}${opName.capitalize()}Utils.kt", code))
    }
    return srcFiles
}