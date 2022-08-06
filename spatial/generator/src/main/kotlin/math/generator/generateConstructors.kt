package math.generator

fun generateConstructors(params: List<NumberType>, defs: List<SpacialDef>): List<SourceFile> {
    val srcFiles = mutableListOf<SourceFile>()
    for (def in defs) {
        val code = buildString {
            appendLine(
                """
                    @file:Suppress("unused", "FunctionName")
                    package math
                    
                    import math.internal.${def.imp}Impl
                    import kotlin.jvm.JvmOverloads
                    import kotlin.jvm.JvmName
                    
                    
                """.trimIndent()
            )
            for (param in params) {
                appendLine("@JvmOverloads")
                appendLine("""@JvmName("${def.iFace}Of${param.label}")""")
                appendLine("fun ${def.iFace}(")
                if (def.hasX) appendLine("\tx: ${param.label} = ${param.default},")
                if (def.hasY) appendLine("\ty: ${param.label} = ${param.default},")
                if (def.hasZ) appendLine("\tz: ${param.label} = ${param.default},")
                append("): ${def.iFace}<${param.label}> = ")
                appendLine("${def.imp}Impl(x, y${if (def.hasZ) ", z" else ""})\n")
            }
        }
        srcFiles.add(SourceFile("${def.iFace}Builders.kt", code))
    }
    return srcFiles
}