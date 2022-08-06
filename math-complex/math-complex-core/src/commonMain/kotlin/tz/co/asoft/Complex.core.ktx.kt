package tz.co.asoft

import kotlin.math.atan
import kotlin.math.sqrt

typealias Z = Complex<*>

fun <N : Number> Z(a: N, b: N) = Complex(a, b)

operator fun Complex<*>.not() = Complex(a, -b.toDouble())

fun Complex<*>.magSquare(): Double {
    val aD = a.toDouble()
    val bD = b.toDouble()
    return (aD * aD) + (bD * bD)
}

fun Complex<*>.mag() = sqrt(magSquare())

fun Complex<*>.arg() = atan(b.toDouble() / a.toDouble())