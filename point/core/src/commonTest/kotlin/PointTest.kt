import expect.expect
import math.Point
import math.plus
import kotlin.test.Test

class PointTest {
    @Test
    fun can_mingle_points_together() {
        val p2 = Point(1, 2.0)
        val p3 = Point(1, 2, 3)

        val p = p2 + p3
        expect(p.x).toBe(2.0)
        expect(p.y).toBe(4.0)
        expect(p.z).toBe(3.0)
    }

    @Test
    fun can_mingle_point2_and_point3() {
        val p2 = Point(1, 2)
        val p3 = Point(1, 2, 3)

        val p = p2 + p3
        expect(p.x).toBe(2.0)
        expect(p.y).toBe(4.0)
        expect(p.z).toBe(3.0)
    }
}