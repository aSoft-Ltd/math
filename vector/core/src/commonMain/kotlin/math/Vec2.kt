@file:Suppress("WRONG_EXPORTED_DECLARATION")

package math

import math.internal.MutableVec2Impl
import kotlin.js.JsExport

@JsExport
interface Vec2<out N> : XY<N> {
    companion object {
        inline operator fun <N> invoke(x: N, y: N): Vec2<N> = MutableVec2Impl(x, y)
    }
}