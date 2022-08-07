package math.generator

fun generateImmutableBinaryOperations(
    params: List<NumberType>,
    ifaces: SpatialInterfaces,
    subpackage: String,
    opName: String,
    opSymbol: String,
): List<SourceFile> {
    val srcFiles = mutableListOf<SourceFile>()
    for ((leftDef, rightDef) in ifaces.toImmutableDefs().permutations()) {
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
                    appendLine("""@JvmName("${leftDef.iFace}${leftParam.label}${opName.capitalize()}${rightDef.iFace}${rightParam.label}")""")
                    append("inline operator fun ${leftDef.iFace}<${leftParam.label}>.")
                    append("$opName(other: ${rightDef.iFace}<${rightParam.label}>) = ")
                    when {
                        !leftDef.hasZ && !rightDef.hasZ -> {
                            append("${rightDef.iFace}(")
                            append("this.x $opSymbol other.x,")
                            append("this.y $opSymbol other.y")
                            appendLine(")\n")
                        }

                        !leftDef.hasZ && rightDef.hasZ -> {
                            append("${rightDef.iFace}(")
                            append("this.x $opSymbol other.x,")
                            append("this.y $opSymbol other.y,")
                            append("${leftParam.default} $opSymbol other.z")
                            appendLine(")\n")
                        }

                        leftDef.hasZ && !rightDef.hasZ -> {
                            append("${leftDef.iFace}(")
                            append("this.x $opSymbol other.x,")
                            append("this.y $opSymbol other.y,")
                            append("this.z $opSymbol ${rightParam.default}")
                            appendLine(")\n")
                        }

                        leftDef.hasZ && rightDef.hasZ -> {
                            append("${leftDef.iFace}(")
                            append("this.x $opSymbol other.x,")
                            append("this.y $opSymbol other.y,")
                            append("this.z $opSymbol other.z")
                            appendLine(")\n")
                        }
                    }
                }
            }
        }
        srcFiles.add(SourceFile("${leftDef.iFace}${opName.capitalize()}${rightDef.iFace}Utils.kt", code))
    }
    return srcFiles
}