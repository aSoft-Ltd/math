package math.generator

private fun NumberType.canBeOperatedWith(other: NumberType) = when (this) {
    NumberType.Short, NumberType.Byte -> false
    NumberType.Int -> listOf(NumberType.Short, NumberType.Byte, NumberType.Int).contains(other)
    NumberType.Long -> other != NumberType.Double && other != NumberType.Float
    NumberType.Float -> other != NumberType.Double
    else -> true
}

fun generateMutableBinaryOperations(
    params: List<NumberType>,
    ifaces: SpatialInterfaces,
    subpackage: String,
    opName: String,
    opSymbol: String,
): List<SourceFile> {
    val srcFiles = mutableListOf<SourceFile>()
    for ((leftDef, rightDef) in ifaces.toMutableDefs().permutations()) {
        val code = buildString {
            appendLine(
                """
                    @file:Suppress("unused", "FunctionName")
                    package math.$subpackage
                    
                    import math.*
                    import kotlin.jvm.JvmName
                    
                """.trimIndent()
            )
            for (leftParam in params) {
                for (rightParam in params) {
                    if (!leftParam.canBeOperatedWith(rightParam)) continue
                    appendLine("""@JvmName("${leftDef.iFace}${leftParam.label}${opName.capitalize()}${rightDef.iFace}${rightParam.label}")""")
                    append("inline operator fun ${leftDef.iFace}<${leftParam.label}>.")
                    appendLine("$opName(other: ${rightDef.iFace}<${rightParam.label}>) {")
                    when {
                        !leftDef.hasZ && !rightDef.hasZ -> {
                            appendLine("\tthis.x $opSymbol= other.x")
                            appendLine("\tthis.y $opSymbol= other.y")
                        }

                        !leftDef.hasZ && rightDef.hasZ -> {
                            appendLine("\tthis.x $opSymbol= other.x")
                            appendLine("\tthis.y $opSymbol= other.y")
                        }

                        leftDef.hasZ && !rightDef.hasZ -> {
                            appendLine("\tthis.x $opSymbol= other.x")
                            appendLine("\tthis.y $opSymbol= other.y")
                            appendLine("\tthis.z $opSymbol= ${rightParam.default}")
                        }

                        leftDef.hasZ && rightDef.hasZ -> {
                            appendLine("\tthis.x $opSymbol= other.x")
                            appendLine("\tthis.y $opSymbol= other.y")
                            appendLine("\tthis.z $opSymbol= other.z")
                        }
                    }
                    appendLine("}\n")
                }
            }
        }
        srcFiles.add(SourceFile("${leftDef.iFace}${opName.capitalize()}${rightDef.iFace}Utils.kt", code))
    }
    return srcFiles
}