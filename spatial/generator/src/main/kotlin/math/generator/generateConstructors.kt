package math.generator

fun generateConstructors(params: List<NumberType>, ifaces: SpacialInterfaces): List<SourceFile> {
    val srcFiles = mutableListOf<SourceFile>()
    for (def in ifaces.toAllDefs()) {
        val code = buildString {
            appendLine(
                """
                    @file:Suppress("unused", "FunctionName")
                    package math
                    
                    import math.internal.${def.imp}
                    import kotlin.jvm.JvmOverloads
                    import kotlin.jvm.JvmName
                    
                    
                """.trimIndent()
            )
            for (param in params) {
                appendLine("@JvmOverloads")
                appendLine("""@JvmName("${def.iFace}Of${param.label}")""")
                appendLine("inline fun ${def.iFace}(")
                if (def.hasX) appendLine("\tx: ${param.label} = ${param.default},")
                if (def.hasY) appendLine("\ty: ${param.label} = ${param.default},")
                if (def.hasZ) appendLine("\tz: ${param.label} = ${param.default},")
                append("): ${def.iFace}<${param.label}> = ")
                appendLine("${def.imp}(x, y${if (def.hasZ) ", z" else ""})\n")
            }

            appendLine("""@JvmName("${def.iFace}Of")""")
            append("inline fun <N> ${def.iFace}(")
            if (def.hasX) append("x: N,")
            if (def.hasY) append("y: N,")
            if (def.hasZ) append("z: N,")
            append("): ${def.iFace}<N> = ${def.imp}(x, y${if (def.hasZ) ", z" else ""})")
        }
        srcFiles.add(SourceFile("${def.iFace}Constructors.kt", code))
    }
    return srcFiles
}