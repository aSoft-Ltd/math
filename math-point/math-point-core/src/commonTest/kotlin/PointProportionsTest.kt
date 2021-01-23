import tz.co.asoft.*
import kotlin.test.Test

class PointProportionsTest {
    @Test
    fun should_correctly_determine_midpoint() {
        val origin = Point2.origin
        val p1 = Point2(4, 4)
        expect(midPointOf(p1, origin)).toBe(Point(2, 2))
    }

    @Test
    fun should_get_non_origin_midpoint() {
        val p1 = Point(2, 8)
        val p2 = Point2(4, 2)
        expect(midPointOf(p1, p2)).toBe(Point(3, 5))
    }
}