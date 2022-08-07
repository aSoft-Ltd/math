package math.generator

fun generateCopyUtils(ifaces: SpatialInterfaces, subpackage: String): List<SourceFile> {
    val srcFiles = mutableListOf<SourceFile>()
    for (def in ifaces.toAllDefs()) {
        val code = buildString {
            appendLine(
                """
                    @file:Suppress("unused", "FunctionName")
                    package math.$subpackage
                    
                    import math.${def.iFace}
                    import math.internal.${def.imp}
                    
                """.trimIndent()
            )
            appendLine("inline fun <N> ${def.iFace}<N>.copy(")
            if (def.hasX) appendLine("\tx: N = this.x,")
            if (def.hasY) appendLine("\ty: N = this.y,")
            if (def.hasZ) appendLine("\tz: N = this.z,")
            appendLine(") : ${def.iFace}<N> = ${def.imp}(x, y${if (def.hasZ) ", z" else ""})\n")
        }
        srcFiles.add(SourceFile("${def.iFace}CopyUtils.kt", code))
    }
    return srcFiles
}