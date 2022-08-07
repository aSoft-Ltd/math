package math.generator

fun generateImmutableScalarOperations(params: List<NumberType>, ifaces: SpatialInterfaces, subpackage: String): List<SourceFile> {
    val srcFiles = mutableListOf<SourceFile>()
    val ops = listOf(
        Operation("times", "*"),
        Operation("div", "/"),
    )
    for (def in ifaces.toImmutableDefs()) {
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
                    for (op in ops) {
                        appendLine("""@JvmName("${def.iFace}${leftParams.label}${op.name.capitalize()}Scalar${rightParams.label}")""")
                        append("inline operator fun ${def.iFace}<${leftParams.label}>")
                        appendLine(".${op.name}(other: ${rightParams.label}) = ${def.iFace}(")
                        if (def.hasX) appendLine("\tx = this.x ${op.symbol} other,")
                        if (def.hasY) appendLine("\ty = this.y ${op.symbol} other,")
                        if (def.hasZ) appendLine("\tz = this.z ${op.symbol} other")
                        appendLine(")\n")

                        appendLine("""@JvmName("Scalar${rightParams.label}${op.name.capitalize()}${def.iFace}${leftParams.label}")""")
                        append("inline operator fun ${rightParams.label}")
                        appendLine(".${op.name}(other: ${def.iFace}<${leftParams.label}>) = ${def.iFace}(")
                        if (def.hasX) appendLine("\tx = this ${op.symbol} other.x,")
                        if (def.hasY) appendLine("\ty = this ${op.symbol} other.y,")
                        if (def.hasZ) appendLine("\tz = this ${op.symbol} other.z")
                        appendLine(")\n")
                    }
                }
            }
        }
        srcFiles.add(SourceFile("${def.iFace}ScalarUtils.kt", code))
    }
    return srcFiles
}