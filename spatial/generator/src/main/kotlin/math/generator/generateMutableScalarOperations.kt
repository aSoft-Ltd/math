package math.generator

fun generateMutableScalarOperations(params: List<NumberType>, ifaces: SpatialInterfaces, subpackage: String): List<SourceFile> {
    val srcFiles = mutableListOf<SourceFile>()
    val ops = listOf(
        Operation("times", "*"),
        Operation("div", "/"),
    )
    for (def in ifaces.toMutableDefs()) {
        val code = buildString {
            appendLine(
                """
                    @file:Suppress("unused", "FunctionName")
                    package math.$subpackage
                    
                    import math.${def.iFace}
                    import kotlin.jvm.JvmOverloads
                    import kotlin.jvm.JvmName
                    import kotlin.js.JsName
                    
                    
                """.trimIndent()
            )
            for (leftParams in params) {
                for (rightParams in params) {
                    if (!leftParams.canBeOperatedWith(rightParams)) continue
                    for (op in ops) {
                        appendLine("""@JvmName("${def.iFace}${leftParams.label}${op.name.capitalize()}Scalar${rightParams.label}")""")
                        append("inline operator fun ${def.iFace}<${leftParams.label}>")
                        appendLine(".${op.name}(other: ${rightParams.label}) {")
                        if (def.hasX) appendLine("\tthis.x ${op.symbol}= other")
                        if (def.hasY) appendLine("\tthis.y ${op.symbol}= other")
                        if (def.hasZ) appendLine("\tthis.z ${op.symbol}= other")
                        appendLine("}\n")

                        appendLine("""@JvmName("Scalar${rightParams.label}${op.name.capitalize()}${def.iFace}${leftParams.label}")""")
                        append("inline operator fun ${rightParams.label}")
                        appendLine(".${op.name}(other: ${def.iFace}<${leftParams.label}>) {")
                        if (def.hasX) appendLine("\tother.x = this ${op.symbol} other.x")
                        if (def.hasY) appendLine("\tother.y = this ${op.symbol} other.y")
                        if (def.hasZ) appendLine("\tother.z = this ${op.symbol} other.z")
                        appendLine("}\n")
                    }
                }
            }
        }
        srcFiles.add(SourceFile("${def.iFace}ScalarUtils.kt", code))
    }
    return srcFiles
}