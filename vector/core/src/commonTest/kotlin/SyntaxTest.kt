import math.vector.times
import math.Vector3.i
import math.Vector3.j
import math.Vector3.k
import math.vector.vecString
import expect.expect
import math.Vec3
import math.vector.plus
import math.vector.minus
import kotlin.test.Test

class SyntaxTest {
    @Test
    fun should_create_a_vector() {
        val v = (2 * i) + (3 * j) - (6 * k)
        expect(v).toBe(Vec3(2, 3, -6))
    }

    @Test
    fun should_display_properly() {
        val v = (2 * i) + (3 * j) - (6 * k)
        expect(v.vecString()).toBe("2i+3j-6k")
        expect(Vec3(2, 0, -3).vecString()).toBe("2i+0j-3k")
        expect(Vec3(1, 1).vecString()).toBe("i+j+0k")
        expect((i - j).vecString()).toBe("i-j+0k")
    }
}