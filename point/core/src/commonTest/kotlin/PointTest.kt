import expect.expect
import math.Point2
import math.Point3
import math.point.plus
import math.spatial.distanceTo
import math.spatial.squareDistanceTo
import kotlin.test.Test

class PointTest {
    @Test
    fun can_mingle_points_together() {
        val p2 = Point3(1.0, 2.0)
        val p3 = Point3(1, 2, 3)

        val p = p2 + p3
        expect(p.x).toBe(2.0)
        expect(p.y).toBe(4.0)
        expect(p.z).toBe(3.0)
    }

    @Test
    fun can_mingle_point2_and_point3() {
        val p2 = Point3(1, 2, 0)
        val p3 = Point3(1, 2, 3)

        val p = p2 + p3
        expect(p.x).toBe(2)
        expect(p.y).toBe(4)
        expect(p.z).toBe(3)
    }

    @Test
    fun can_measure_distances() {
        val p3 = Point2(3, 4)
        val o = Point2(0, 0)
        expect(p3.squareDistanceTo(o)).toBe(25)
        expect(p3.distanceTo(o)).toBe(5.0)
    }
}