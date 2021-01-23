import tz.co.asoft.*
import kotlin.test.Test

class PointDistanceTest {
    @Test
    fun should_have_a_distance_of_5_from_origin() {
        val origin = Point2.origin
        val p1 = Point2(3, 4)
        expect(p1.squareDistanceTo(origin)).toBe(25.0)
        expect(p1.distanceTo(origin)).toBe(5.0)
    }
}