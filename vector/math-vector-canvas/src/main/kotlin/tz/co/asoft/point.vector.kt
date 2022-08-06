package tz.co.asoft

fun Point<*>.asVec() = Vec(x, y)

fun Vec<*>.asPoint() = Point(x, y)