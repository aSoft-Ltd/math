import expect.expect
import math.Vec3
import math.Vector.i
import math.Vector.j
import math.Vector.k
import math.vector.times
import math.vector.x
import kotlin.test.Test

class VectorMultiplicationTest {
    @Test
    fun should_have_a_correct_dot_product() {
        expect(Vec3(1, 1, 1) * Vec3(0, 0, 0)).toBe(0)
    }

    /*
    @Test
    fun should_have_a_correct_cross_product() {
        expect(i x j).toBe(k)
        expect(j x i).toBe(-k)

        expect(j x k).toBe(i)
        expect(k x j).toBe(-i)

        expect(k x i).toBe(j)
        expect(i x k).toBe(-j)

        expect(i x k).toBe(-j)
        expect(k x i).toBe(j)
    }

    @Test
    fun should_obey_math_laws() {
        expect(i x j) {
            toBe(-(j x i))
            toBe(-j x i)
            toBe(j x -i)
        }
    }
     */
}