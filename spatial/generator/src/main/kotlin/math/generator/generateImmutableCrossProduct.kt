package math.generator

fun generateImmutableCrossProduct(
    params: List<NumberType>,
    ifaces: SpatialInterfaces,
): List<SourceFile> {
    val srcFiles = mutableListOf<SourceFile>()
    for ((leftDef, rightDef) in ifaces.toImmutableDefs().permutations()) {
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
                    appendLine("""@JvmName("${leftDef.iFace}${leftParam.label}Cross${rightDef.iFace}${rightParam.label}")""")
                    append("inline infix fun ${leftDef.iFace}<${leftParam.label}>.")
                    append("cross(vec: ${rightDef.iFace}<${rightParam.label}>) = ")
                    when {
                        leftDef.hasZ && rightDef.hasZ -> {
                            appendLine("Vec3(")
                            appendLine("\tx = (y * vec.z) - (z * vec.y),")
                            appendLine("\ty = (z * vec.x) - (x * vec.z),")
                            appendLine("\tz = (x * vec.y) - (y * vec.x)")
                            appendLine(")\n")
                        }

                        !leftDef.hasZ && rightDef.hasZ -> {
                            appendLine("this.toVec3() cross vec\n")
                        }

                        leftDef.hasZ && !rightDef.hasZ -> {
                            appendLine("this cross vec.toVec3()\n")
                        }

                        !leftDef.hasZ && !rightDef.hasZ -> {
                            appendLine("this.toVec3() cross vec.toVec3()\n")
                        }
                    }

                    appendLine("""@JvmName("${leftDef.iFace}${leftParam.label}X${rightDef.iFace}${rightParam.label}")""")
                    append("inline infix fun ${leftDef.iFace}<${leftParam.label}>.")
                    appendLine("x(vec: ${rightDef.iFace}<${rightParam.label}>) = this cross vec\n")
                }
            }
        }
        srcFiles.add(SourceFile("${leftDef.iFace}Cross${rightDef.iFace}Utils.kt", code))
    }
    return srcFiles
}