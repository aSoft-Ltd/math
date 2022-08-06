package tz.co.asoft

inline class Decimal(val value: Double) : Numeral<Double> {
    constructor(value: Number) : this(value.toDouble())

    operator fun plus(other: Decimal) = Decimal(value + other.value)
}

