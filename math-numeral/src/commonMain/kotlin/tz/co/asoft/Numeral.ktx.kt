package tz.co.asoft

fun <T : Number> Numeral(value: T): Numeral<T> = object : Numeral<T> {
    val value: T = value
}