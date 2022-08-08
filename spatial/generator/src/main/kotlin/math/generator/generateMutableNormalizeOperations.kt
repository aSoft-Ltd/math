package math.generator

fun generateMutableNormalizeOperations(
    params: List<NumberType>,
    ifaces: SpatialInterfaces,
): List<SourceFile> {
    val srcFiles = mutableListOf<SourceFile>()
    for (def in ifaces.toMutableDefs()) {

        val code = buildString {
            appendLine(
                """
                    @file:Suppress("unused")
                    package math.vector
                    
                    import math.${def.iFace}
                    import math.sqrt
                    import kotlin.jvm.JvmName
                    
                    
                """.trimIndent()
            )
            for (param in params) {
                if (param !in NumberType.Floats) continue
                appendLine("""@JvmName("normalize${param.label}${def.iFace}")""")
                appendLine("inline fun ${def.iFace}<${param.label}>.normalize() {")
                appendLine("\tval mag = magnitude")
                if (def.hasX) appendLine("\tx /= mag")
                if (def.hasY) appendLine("\ty /= mag")
                if (def.hasZ) appendLine("\tz /= mag")
                appendLine("}\n")
            }
        }
        srcFiles.add(SourceFile("${def.iFace}NormalUtils.kt", code))
    }
    return srcFiles
}