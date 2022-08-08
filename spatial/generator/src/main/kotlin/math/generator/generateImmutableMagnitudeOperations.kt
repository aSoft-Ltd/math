package math.generator

fun generateImmutableMagnitudeOperations(
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
                    import math.sqrt
                    import kotlin.jvm.JvmName
                    
                    
                """.trimIndent()
            )
            for (param in params) {
                appendLine("""@get:JvmName("squareMagnitudeOf${param.label}${def.iFace}")""")
                append("inline val ${def.iFace}<${param.label}>.")
                append("squareMagnitude get() =")
                if (def.hasX) append("  (x * x) ")
                if (def.hasY) append("+ (y * y) ")
                if (def.hasZ) append("+ (z * z)")
                appendLine("\n")

                appendLine("""@get:JvmName("magnitudeOf${param.label}${def.iFace}")""")
                append("inline val ${def.iFace}<${param.label}>.")
                appendLine("magnitude get() = sqrt(squareMagnitude)\n")
            }
        }
        srcFiles.add(SourceFile("${def.iFace}MagnitudeUtils.kt", code))
    }
    return srcFiles
}