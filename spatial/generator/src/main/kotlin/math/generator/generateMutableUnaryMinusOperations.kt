package math.generator

fun generateMutableUnaryMinusOperations(
    params: List<NumberType>, ifaces: SpatialInterfaces, subpackage: String
): List<SourceFile> {
    val srcFiles = mutableListOf<SourceFile>()
    for (def in ifaces.toMutableDefs()) {
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
                if (!param.canBeOperatedWith(param)) continue
                appendLine("""@JvmName("negativeOf${param.label}${def.iFace}")""")
                appendLine("inline operator fun ${def.iFace}<${param.label}>.unaryMinus() { ")
                if (def.hasX) appendLine("\tx = -x")
                if (def.hasY) appendLine("\ty = -y")
                if (def.hasZ) appendLine("\tz = -z")
                appendLine("}\n")
            }
        }
        srcFiles.add(SourceFile("${def.iFace}UnaryMinusUtils.kt", code))
    }
    return srcFiles
}