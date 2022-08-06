package tz.co.asoft

operator fun Decimal.plus(other: Decimal) = Decimal(value + other.value)

