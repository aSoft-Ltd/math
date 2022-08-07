package math.generator

fun generateImmutableDotProduct(
    params: List<NumberType>,
    ifaces: SpatialInterfaces,
): List<SourceFile> {
    val srcFiles = mutableListOf<SourceFile>()
    val toBeDoted = listOf(
        SpatialDef.fromXY(ifaces.xy) to SpatialDef.fromXY(ifaces.xy),
        SpatialDef.fromXYZ(ifaces.xyz) to SpatialDef.fromXYZ(ifaces.xyz)
    )
    for ((leftDef, rightDef) in toBeDoted) {
        val code = buildString {
            appendLine(
                """
                    @file:Suppress("unused", "FunctionName")
                    package math.vector
                    
                    import math.*
                    import kotlin.jvm.JvmName
                    
                """.trimIndent()
            )
            for (leftParam in params) {
                for (rightParam in params) {
                    appendLine("""@JvmName("${leftDef.iFace}${leftParam.label}Dot${rightDef.iFace}${rightParam.label}")""")
                    append("inline infix fun ${leftDef.iFace}<${leftParam.label}>.")
                    append("dot(vec: ${rightDef.iFace}<${rightParam.label}>) = ")
                    when {
                        leftDef.hasZ && rightDef.hasZ -> {
                            append("(this.x * vec.x) + ")
                            append("(this.y * vec.y) + ")
                            append("(this.z * vec.z)")
                        }

                        else -> {
                            append("(this.x * vec.x) + ")
                            append("(this.y * vec.y)")
                        }
                    }
                    appendLine("\n")

                    appendLine("""@JvmName("${leftDef.iFace}${leftParam.label}Times${rightDef.iFace}${rightParam.label}")""")
                    append("inline infix operator fun ${leftDef.iFace}<${leftParam.label}>.")
                    appendLine("times(vec: ${rightDef.iFace}<${rightParam.label}>) = this dot vec\n")
                }
            }
        }
        srcFiles.add(SourceFile("${leftDef.iFace}Dot${rightDef.iFace}Utils.kt", code))
    }
    return srcFiles
}