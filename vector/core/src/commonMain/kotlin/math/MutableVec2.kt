@file:Suppress("WRONG_EXPORTED_DECLARATION")

package math

import math.internal.MutableVec2Impl
import kotlin.js.JsExport

@JsExport
interface MutableVec2<N> : Vec2<N>, MutableXY<N> {
    companion object {
        inline operator fun <N> invoke(x: N, y: N): MutableVec2<N> = MutableVec2Impl(x, y)
    }
}