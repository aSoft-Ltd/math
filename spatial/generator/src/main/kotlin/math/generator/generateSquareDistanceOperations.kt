package math.generator

fun generateDistanceOperations(
    params: List<NumberType>, defs: List<SpacialDef>
): List<SourceFile> {
    val srcFiles = mutableListOf<SourceFile>()
    for (def in defs) {

        val code = buildString {
            appendLine(
                """
                    @file:Suppress("unused", "FunctionName")
                    package math.point
                    
                    import math.${def.iFace}
                    import math.sqrt
                    import kotlin.jvm.JvmName
                    
                    
                """.trimIndent()
            )
            for (left in params) {
                for (right in params) {
                    appendLine("""@JvmName("${left.label}PointSquareDistanceTo${right.label}Point")""")
                    append("inline fun ${def.iFace}<${left.label}>.")
                    append("squareDistanceTo(point: ${def.iFace}<${right.label}>) =")
                    if (def.hasX) append("  ((x - point.x) * (x - point.x)) ")
                    if (def.hasY) append("+ ((y - point.y) * (y - point.y)) ")
                    if (def.hasZ) append("+ ((z - point.z) * (z - point.z)) ")
                    appendLine("\n")

                    appendLine("""@JvmName("${left.label}PointDistanceTo${right.label}Point")""")
                    append("inline fun ${def.iFace}<${left.label}>.")
                    appendLine("distanceTo(point: ${def.iFace}<${right.label}>) = sqrt(squareDistanceTo(point))\n")
                }
            }
        }
        srcFiles.add(SourceFile("${def.iFace}DistanceUtils.kt", code))
    }
    return srcFiles
}