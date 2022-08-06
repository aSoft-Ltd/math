package tz.co.asoft

operator fun Complex<*>.minus(other: Number) = Complex(
    a = a.toDouble() - other.toDouble(),
    b = b.toDouble()
)

operator fun Complex<*>.minus(other: Complex<*>) = Complex(
    a = a.toDouble() - other.a.toDouble(),
    b = b.toDouble() - other.b.toDouble()
)

operator fun Number.minus(other: Complex<*>) = Complex(
    a = toDouble() - other.a.toDouble(),
    b = -other.b.toDouble()
)

operator fun Complex<*>.unaryMinus() = Complex(
    a = -a.toDouble(),
    b = -b.toDouble()
)