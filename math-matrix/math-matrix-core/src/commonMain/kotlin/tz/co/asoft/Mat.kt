package tz.co.asoft

data class Mat<N : Number> internal constructor(
    val rows: List<Row<N>>
) {
    init {
        val row1 = rows.firstOrNull()
        rows.forEach {
            require(it.size == row1?.size) { "Every row of a matrix should have equal number of column" }
        }
    }

    companion object {
        fun zero(rows: Int, cols: Int) = Mat(List(rows) { Row(MutableList(cols) { 0.0 }) })
    }

    val size = Size(rows.size, rows.firstOrNull()?.size ?: 0)

    data class Row<N : Number>(val cols: MutableList<N>) {
        val size = cols.size
        operator fun get(index: Int) = cols[index - 1]
        operator fun set(index: Int, value: N) {
            cols[index - 1] = value
        }
    }

    data class Size(val rows: Int, val cols: Int) {
        override fun toString(): String = "${rows}x${cols}"
    }

    operator fun get(index: Int): Row<N> {
        require(index in 1..rows.size) { "" }
        return rows[index - 1]
    }

    inline fun forEach(looper: (r: Int, c: Int) -> Unit) {
        for (r in 1..size.rows) {
            for (c in 1..size.cols) looper(r, c)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (other is Mat<*>) {
            if (size != other.size) return false
            forEach { r, c ->
                try {
                    if (!this[r][c].isEqualTo(other[r][c])) {
                        return false
                    }
                } catch (c: Error) {
                    return false
                }
            }
            return true
        } else return false
    }
}