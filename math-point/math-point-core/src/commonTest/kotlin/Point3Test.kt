import tz.co.asoft.*
import kotlin.test.Test

class Point3Test {
    private val p1 = Point3(0, 0, 0)
    private val p2 = Point3(3, 4, 5)
    private val p3 = Point3(3.1, 4.0, 6.5)

    @Test
    fun should_have_a_proper_x_and_y() {
        expect(p1.x).toBe(0)
        expect(p2.x).toBe(3)
    }

    @Test
    fun should_display_point_to_string_well() {
        expect(p2.toString()).toBe("Point(x=3,y=4,z=5)")
        expect(p2.cordString()).toBe("(3,4,5)")
    }

    @Test
    fun should_desctructure_point_well() {
        val (x, y, z) = p3
        expect(x).toBe(3.1)
        expect(y).toBe(4.0)
        expect(z).toBe(6.5)
    }

    @Test
    fun can_copy_a_long_int_from_a_long() {
        val p = p1.copy(x = 6.5)
        expect(p.x).toBe(6.5)
        expect(p.y).toBe(0)
    }
}