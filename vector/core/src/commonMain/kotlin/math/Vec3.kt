package math

import kotlin.js.JsExport

@JsExport
interface Vec3<out N> : Vec2<N>, XYZ<N>