package math.generator

fun generateImmutableDotOperationsForVectors(
    params: List<NumberType>,
    defs: List<SpacialDef>,
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
                    appendLine("""@JvmName("${def.iFace}${left.label}Dot${def.iFace}${right.label}")""")
                    append("inline infix fun ${def.iFace}<${left.label}>.")
                    append("dot(vec: ${def.iFace}<${right.label}>) =")
                    if (def.hasX) append("   (x * vec.x)")
                    if (def.hasY) append(" + (y * vec.y)")
                    if (def.hasZ) append(" + (z * vec.z)")
                    appendLine("\n")

                    appendLine("""@JvmName("${def.iFace}${left.label}Times${def.iFace}${right.label}")""")
                    append("inline infix operator fun ${def.iFace}<${left.label}>.")
                    append("times(vec: ${def.iFace}<${right.label}>) =")
                    if (def.hasX) append("   (x * vec.x)")
                    if (def.hasY) append(" + (y * vec.y)")
                    if (def.hasZ) append(" + (z * vec.z)")
                    appendLine("\n")
                }
            }
        }
        srcFiles.add(SourceFile("${def.iFace}DotUtils.kt", code))
    }
    return srcFiles
}