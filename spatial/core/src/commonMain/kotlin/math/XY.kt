package math

import kotlin.js.JsExport

@JsExport
interface XY<out N> : X<N>, Y<N>