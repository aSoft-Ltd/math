package tz.co.asoft

data class Complex<T : Number>(val a: T, val b: T) {
    companion object {
        val ZERO = Complex(0, 0)
        val i = Complex(0, 1)
    }

    override fun hashCode(): Int = toString().hashCode()

    override fun equals(other: Any?): Boolean = when (other) {
        is Number -> b.isZero() && other.isEqualTo(a)
        is Complex<*> -> a.isEqualTo(other.a) && b.isEqualTo(other.b)
        else -> false
    }

    override fun toString() = when {
        b.isZero() && a.isZero() -> "0"
        b.isZero() && a.isNotZero() -> a.toReadableString()
        b.isNotZero() && a.isZero() -> if (b.toDouble() <= 0.0) {
            val sB = b.signString() + (-b.toDouble()).toReadableString(emptyIfOne = true)
            "${sB}i"
        } else {
            val sB = b.toReadableString(emptyIfOne = true)
            "${sB}i"
        }
        b.isNotZero() && a.isNotZero() -> {
            val sA = a.toReadableString()
            val sB = b.signString() + b.toReadableString(emptyIfOne = true)
            "$sA${sB}i"
        }
        else -> throw IllegalStateException("Can't reach this state")
    }
}