package math.generator

fun generateInternalConvertors(
    params: List<NumberType>, ifaces: SpatialInterfaces, subpackage: String
): List<SourceFile> {
    val srcFiles = mutableListOf<SourceFile>()

    val conversions = ifaces.toAllDefs().permutations()

    for ((from, to) in conversions) {
        if (from.iFace == to.iFace || from.hasZ == to.hasZ) continue
        val code = buildString {
            appendLine(
                """
                    @file:Suppress("unused", "FunctionName")
                    package math.$subpackage
                    
                    import math.${from.iFace}
                    import math.${to.iFace}
                    import math.internal.${to.imp}
                    import kotlin.jvm.JvmOverloads
                    import kotlin.jvm.JvmName
                    
                    
                """.trimIndent()
            )
            when {
                !from.hasZ && to.hasZ -> {
                    for (param in params) {
                        appendLine("""@JvmName("convertFrom${from.iFace}${param.label}To${to.iFace}${param.label}")""")
                        append("inline fun ${from.iFace}<${param.label}>.to${to.iFace}(")
                        append("x: ${param.label} = this.x, ")
                        append("y: ${param.label} = this.y, ")
                        append("z: ${param.label} = ${param.default}) : ${to.iFace}<${param.label}> = ")
                        appendLine("${to.imp}(x,y,z)\n")
                    }
                    appendLine("""@JvmName("convertFrom${from.iFace}To${to.iFace}")""")
                    append("inline fun <N> ${from.iFace}<N>.to${to.iFace}(x: N = this.x, y: N = this.y, z: N) : ${to.iFace}<N> = ${to.imp}(x,y,z)")
                }

                from.hasZ && !to.hasZ -> {
                    appendLine("""@JvmName("convertFrom${from.iFace}To${to.iFace}")""")
                    append("inline fun <N> ${from.iFace}<N>.to${to.iFace}(x: N = this.x, y: N = this.y) : ${to.iFace}<N> = ${to.imp}(x,y)\n")
                }

                else -> {}
            }
        }
        srcFiles.add(SourceFile("${from.iFace}To${to.iFace}ConvertorUtils.kt", code))
    }
    return srcFiles
}