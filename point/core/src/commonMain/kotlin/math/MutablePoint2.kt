@file:Suppress("WRONG_EXPORTED_DECLARATION")
package math

import kotlin.js.JsExport

@JsExport
interface MutablePoint2<N> : Point2<N>, MutableXY<N>