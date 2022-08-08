package math.generator

fun generateImmutableDistanceOperations(
    params: List<NumberType>, ifaces: SpatialInterfaces, subpackage: String
): List<SourceFile> {
    val srcFiles = mutableListOf<SourceFile>()
    for (def in ifaces.toImmutableDefs()) {

        val code = buildString {
            appendLine(
                """
                    @file:Suppress("unused", "FunctionName")
                    package math.$subpackage
                    
                    import math.${def.iFace}
                    import math.sqrt
                    import kotlin.jvm.JvmName
                    
                    
                """.trimIndent()
            )
            for ((left, right) in params.permutations()) {
                appendLine("""@JvmName("${left.label}${def.iFace}SquareDistanceTo${right.label}${def.iFace}")""")
                append("inline fun ${def.iFace}<${left.label}>.")
                append("squareDistanceTo(point: ${def.iFace}<${right.label}>) =")
                if (def.hasX) append("  ((x - point.x) * (x - point.x)) ")
                if (def.hasY) append("+ ((y - point.y) * (y - point.y)) ")
                if (def.hasZ) append("+ ((z - point.z) * (z - point.z)) ")
                appendLine("\n")

                appendLine("""@JvmName("${left.label}${def.iFace}DistanceTo${right.label}${def.iFace}")""")
                append("inline fun ${def.iFace}<${left.label}>.")
                appendLine("distanceTo(point: ${def.iFace}<${right.label}>) = sqrt(squareDistanceTo(point))\n")
            }
        }
        srcFiles.add(SourceFile("${def.iFace}DistanceUtils.kt", code))
    }
    return srcFiles
}