package tz.co.asoft

operator fun Complex<*>.times(other: Number) = Complex(
    a = a.toDouble() * other.toDouble(),
    b = b.toDouble() * other.toDouble()
)

/**
 * (a1+ib1)(a2+ib2)
 * (a1*a2 + ia1*b2 + ia2*b1 - b1b2)
 */
operator fun Complex<*>.times(other: Complex<*>): Complex<Double> {
    val z1 = this
    val z2 = other
    return Complex(
        a = z1.a.toDouble() * z2.a.toDouble() - z1.b.toDouble() * z2.b.toDouble(),
        b = z1.a.toDouble() * z2.b.toDouble() - z1.b.toDouble() * z2.a.toDouble()
    )
}

operator fun Number.times(other: Complex<*>) = other * this