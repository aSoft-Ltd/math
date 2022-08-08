import expect.expect
import math.MutableVec2
import math.Vec2
import math.vector.normal
import math.vector.normalize
import kotlin.test.Test

class NormalizeTest {
    @Test
    fun should_be_able_to_normalize() {
        val v = Vec2(3, 4)
        println("Testing if ${v.normal}==${v.normal}")
        expect(v.normal).toBe(v.normal)
    }

    @Test
    fun should_able_to_renormalized_a_nomralied_vector() {
        val v = MutableVec2(6.0, 8.0)
        println("Unormalized v: $v")
        v.normalize()
        v.normalize()
        println("Normalized v: $v")
        expect(v.normal).toBe(v)
    }
}