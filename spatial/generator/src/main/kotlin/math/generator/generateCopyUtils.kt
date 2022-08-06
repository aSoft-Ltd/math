package math.generator

fun generateCopyUtils(params: List<NumberType>, defs: List<SpacialDef>): List<SourceFile> {
    val srcFiles = mutableListOf<SourceFile>()
    for (def in defs) {
        val code = buildString {
            appendLine(
                """
                    @file:Suppress("unused", "FunctionName")
                    package math.point
                    
                    import math.${def.iFace}
                    
                """.trimIndent()
            )
            for (param in params) {
                appendLine("inline fun ${def.iFace}<${param.label}>.copy(")
                if (def.hasX) appendLine("\tx: ${param.label} = this.x,")
                if (def.hasY) appendLine("\ty: ${param.label} = this.y,")
                if (def.hasZ) appendLine("\tz: ${param.label} = this.z,")
                appendLine(") = ${def.iFace}(x, y${if (def.hasZ) ", z" else ""})\n")
            }
        }
        srcFiles.add(SourceFile("${def.iFace}CopyUtils.kt", code))
    }
    return srcFiles
}