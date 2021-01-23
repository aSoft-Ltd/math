package tz.co.asoft

import kotlin.math.sign

fun Number.signString() = if (sign(toDouble()) < 0) "-" else "+"