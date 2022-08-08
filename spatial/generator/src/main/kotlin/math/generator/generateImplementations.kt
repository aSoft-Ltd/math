package math.generator

fun generateImplementations(params: List<NumberType>, ifaces: SpatialInterfaces): List<SourceFile> {
    val srcFiles = mutableListOf<SourceFile>()
    for (def in ifaces.toMutableDefs()) {
        val code = buildString {
            appendLine(
                """
                    package math.internal
                    
                    import math.${def.iFace}
                    
                """.trimIndent()
            )
            appendLine("@PublishedApi")
            appendLine("internal data class ${def.imp}<N>(")
            if (def.hasX) appendLine("\toverride var x: N,")
            if (def.hasY) appendLine("\toverride var y: N,")
            if (def.hasZ) appendLine("\toverride var z: N")
            appendLine("): ${def.iFace}<N> {")
            append("""${"\t"}override fun toString() = "(x=${'$'}x""")
            if (def.hasY) append(", y=${'$'}y")
            if (def.hasZ) append(", z=${'$'}z")
            appendLine(""")"""")
            appendLine("}\n")
        }
        srcFiles.add(SourceFile("${def.imp}.kt", code))
    }
    return srcFiles
}