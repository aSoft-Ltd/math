package tz.co.asoft

inline class Integer(val value: Long) : Numeral<Long> {
    operator fun plus(other: Integer) = Integer(value + other.value)
}