package tz.co.asoft

operator fun Complex<*>.div(other: Number) = Complex(
    a = a.toDouble() / other.toDouble(),
    b = b.toDouble() / other.toDouble()
)

/**
 * (a1+ib1)/(a2+ib2)
 * (a1+ib1)(a2-ib2)/(a2+ib2)(a2-ib2)
 */
operator fun Complex<*>.div(other: Complex<*>): Complex<Double> = (this * !other) / other.magSquare()

operator fun Number.div(other: Complex<*>) = Z(this, 0) / other