package tz.co.asoft

fun Number.isEqualTo(other: Number): Boolean {
    if (isZero() && other.isZero()) return true
    if (toDouble() == other.toDouble()) return true
    return false
}