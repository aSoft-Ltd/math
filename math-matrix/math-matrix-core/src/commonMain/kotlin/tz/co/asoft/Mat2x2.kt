package tz.co.asoft

class Mat2x2<N : Numeral<*>>(val a: N, val b: N, val c: N, val d: N) {
    operator fun plus(other: Mat2x2<*>) = Mat2x2(a + other.a, b + other.b, c + other.c, d + other.d)
}