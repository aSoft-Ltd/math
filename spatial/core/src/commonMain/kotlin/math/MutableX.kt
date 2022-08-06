@file:Suppress("WRONG_EXPORTED_DECLARATION")
package math

import kotlin.js.JsExport

@JsExport
interface MutableX<N> : X<N> {
    override var x: N
}