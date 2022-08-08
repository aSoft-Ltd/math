package math.generator

fun generateImmutableUnaryMinusOperations(
    params: List<NumberType>, ifaces: SpatialInterfaces, subpackage: String
): List<SourceFile> {
    val srcFiles = mutableListOf<SourceFile>()
    for (def in ifaces.toImmutableDefs()) {

        val code = buildString {
            appendLine(
                """
                    @file:Suppress("unused")
                    package math.$subpackage
                    
                    import math.${def.iFace}
                    import kotlin.jvm.JvmName
                    
                    
                """.trimIndent()
            )
            for (param in params) {
                appendLine("""@JvmName("negativeOf${param.label}${def.iFace}")""")
                append("inline operator fun ${def.iFace}<${param.label}>.")
                append("unaryMinus() = ${def.iFace}(")
                if (def.hasX) append(" -x,")
                if (def.hasY) append(" -y,")
                if (def.hasZ) append(" -z")
                appendLine(")\n")
            }
        }
        srcFiles.add(SourceFile("${def.iFace}UnaryMinusUtils.kt", code))
    }
    return srcFiles
}