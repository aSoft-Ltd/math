package tz.co.asoft

operator fun Complex<*>.plus(other: Number) = Complex(
    a = a.toDouble() + other.toDouble(),
    b = b.toDouble()
)

operator fun Complex<*>.plus(other: Complex<*>) = Complex(
    a = a.toDouble() + other.a.toDouble(),
    b = b.toDouble() + other.b.toDouble()
)

operator fun Number.plus(other: Complex<*>) = other + this