@file:Suppress("WRONG_EXPORTED_DECLARATION")

package math

import kotlin.js.JsExport

@JsExport
interface MutableXYZ<N> : XYZ<N>, MutableXY<N>, MutableZ<N>