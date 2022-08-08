package math.generator

fun generateImmutableNormalizeOperations(
    params: List<NumberType>,
    ifaces: SpatialInterfaces,
): List<SourceFile> {
    val srcFiles = mutableListOf<SourceFile>()
    for (def in ifaces.toImmutableDefs()) {

        val code = buildString {
            appendLine(
                """
                    @file:Suppress("unused")
                    package math.vector
                    
                    import math.${def.iFace}
                    import math.sqrt
                    import kotlin.jvm.JvmName
                    
                    
                """.trimIndent()
            )
            for (param in params) {
                appendLine("""@get:JvmName("normalOf${param.label}${def.iFace}")""")
                appendLine("inline val ${def.iFace}<${param.label}>.normal get() = this / magnitude\n")
            }
        }
        srcFiles.add(SourceFile("${def.iFace}NormalUtils.kt", code))
    }
    return srcFiles
}