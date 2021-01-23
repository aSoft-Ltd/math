package tz.co.asoft

import kotlin.math.sqrt

fun Vec2<*>.squareDistanceTo(v: Vec2<*>) = (this - v).squareMagnitude()

fun Vec2<*>.distanceTo(v: Vec2<*>) = sqrt(squareDistanceTo(v))

fun Vec2<*>.squareDistanceTo(v: Vec3<*>) = (this - v).squareMagnitude()

fun Vec2<*>.distanceTo(v: Vec3<*>) = sqrt(squareDistanceTo(v))

fun Vec3<*>.squareDistanceTo(v: Vec2<*>) = (this - v).squareMagnitude()

fun Vec3<*>.distanceTo(v: Vec2<*>) = sqrt(squareDistanceTo(v))

fun Vec3<*>.squareDistanceTo(v: Vec3<*>) = (this - v).squareMagnitude()

fun Vec3<*>.distanceTo(v: Vec3<*>) = sqrt(squareDistanceTo(v))