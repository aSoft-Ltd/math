package tz.co.asoft

data class Mat2x2<N : Number>(val a: N, val b: N, val c: N, val d: N) {
    fun toStringLines() = """
     | ${a.toReadableString()}${"\t\t"}${b.toReadableString()} |
     | ${c.toReadableString()}${"\t\t"}${d.toReadableString()} |
    """.trimIndent()
}