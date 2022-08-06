package tz.co.asoft

operator fun Mat<*>.plus(other: Mat<*>): Mat<Double> {
    require(size == other.size) { "Can only add matrices with matching dimensions. $size and ${other.size} do to match" }
    val m1 = this
    val m2 = other
    val res = Mat.zero(size.rows, size.cols)
    forEach { r, c ->
        res[r][c] = m1[r][c].toDouble() + m2[r][c].toDouble()
    }
    return res
}

operator fun Mat<*>.minus(other: Mat<*>): Mat<Double> {
    require(size == other.size) { "Can only subtract matrices with matching dimensions. $size and ${other.size} do to match" }
    val m1 = this
    val m2 = other
    val res = Mat.zero(size.rows, size.cols)
    forEach { r, c ->
        res[r][c] = m1[r][c].toDouble() - m2[r][c].toDouble()
    }
    return res
}

operator fun Mat<*>.times(other: Mat<*>): Mat<Double> {
    require(size == other.size) { "Can only multiply matrices with matching dimensions. $size and ${other.size} do to match" }
    val m1 = this
    val m2 = other
    val res = Mat.zero(size.rows, size.cols)
    forEach { r, c ->
        res[r][c] = m1[r][c].toDouble() * m2[r][c].toDouble()
    }
    return res
}

operator fun Mat<*>.times(other: Number): Mat<Double> {
    val res = Mat.zero(size.rows, size.cols)
    forEach { r, c ->
        res[r][c] = this[r][c].toDouble() * other.toDouble()
    }
    return res
}