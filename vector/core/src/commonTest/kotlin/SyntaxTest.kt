import math.Vec
import math.times
import math.Vector.i
import math.Vector.j
import math.Vector.k
import math.vecString
import tz.co.asoft.*
import kotlin.test.Test

class SyntaxTest {
    @Test
    fun should_create_a_vector() {
        val v = (2 * i) + (3 * j) - (6 * k)
        expect(v).toBe(Vec(2.0, 3.0, -6.0))
    }

    @Test
    fun should_display_properly() {
        val v = (2 * i) + (3 * j) - (6 * k)
        expect(v.vecString()).toBe("2i+3j-6k")
        expect(Vec(2, 0, -3).vecString()).toBe("2i+0j-3k")
        expect(Vec(1, 1).vecString()).toBe("i+j+0k")
        expect((i - j).vecString()).toBe("i-j+0k")
    }
}