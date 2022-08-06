@file:Suppress("WRONG_EXPORTED_DECLARATION")

package math

import kotlin.js.JsExport

@JsExport
interface XY<out N> : X<N>, Y<N>