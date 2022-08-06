@file:Suppress("WRONG_EXPORTED_DECLARATION")

package math

import math.internal.MutableVec3Impl
import kotlin.js.JsExport

@JsExport
interface MutableVec3<N> : Vec3<N>, MutableVec2<N>, MutableXYZ<N> {
    companion object {
        inline operator fun <N> invoke(x: N, y: N, z: N): MutableVec3<N> = MutableVec3Impl(x, y, z)
    }
}