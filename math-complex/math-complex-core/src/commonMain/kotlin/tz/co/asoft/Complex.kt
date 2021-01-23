package tz.co.asoft

class Complex<T : Number>(val a: T, val b: T) : Numeral<T> {
    operator fun plus(other: Numeral<*>) = when (other) {
        is Decimal -> Complex(a.toDouble() + other.value, b)
        is Integer -> Complex(a.toDouble() + other.value, b)
        is Complex<*> -> Complex(a.toDouble() + other.a.toDouble(), b.toDouble() + other.b.toDouble())
        else -> throw IllegalArgumentException("Can't add an unknown to a ComplexNumber")
    }

    override fun hashCode(): Int = toString().hashCode()

    override fun equals(other: Any?): Boolean = when (other) {
        is Number -> b.isZero() && other.isEqualTo(a)
        is Decimal -> equals(other.value)
        is Integer -> equals(other.value)
        is Complex<*> -> a.isEqualTo(other.a) && b.isEqualTo(other.b)
        else -> throw IllegalArgumentException("Can't add an unknown to a ComplexNumber")
    }

    override fun toString(): String {
        val sA = a.toReadableString()
        val sB = b.signString() + b.toReadableString(emptyIfOne = true)
        return "$sA${sB}i"
    }
}