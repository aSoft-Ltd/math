package math

import kotlin.math.sign

fun Number.isEqualTo(other: Number): Boolean {
    if (isZero() && other.isZero()) return true
    if (toDouble() == other.toDouble()) return true
    return false
}

inline fun Number.signString() = if (sign(toDouble()) < 0) "-" else "+"

inline fun Number.isNotZero() = !isZero()

fun Number.isZero(): Boolean {
    if (this == 0 || this == -0) return true
    if (this == 0.0 || this == -0.0) return true
    if (this == 0f || this == -0f) return true
    if (this == 0L || this == -0L) return true
    return false
}

fun Number.toReadableString(emptyIfOne: Boolean = false): String {
    val str = if ((this is Double || this is Float) && this.toString().endsWith(".0")) {
        toString().replace(".0", "")
    } else toString()

    return if (emptyIfOne && str == "1") "" else str
}