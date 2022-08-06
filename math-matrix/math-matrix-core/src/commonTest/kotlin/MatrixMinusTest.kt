import tz.co.asoft.*
import kotlin.test.Test
import kotlin.test.assertFails

class MatrixMinusTest {
    @Test
    fun should_subtract_2_by_2matrix_well() {
        val m1 = Mat(
            1 to 1,
            1 to 2
        )

        val m2 = Mat(
            1 to 2,
            1 to 1
        )

        val res = Mat(
            0 to -1,
            0 to 1
        )
        expect(m1 - m2).toBe(res)
    }

    @Test
    fun should_fail_to_subtract_two_different_matrices() {
        assertFails {
            Mat(1 to 2) - Mat(1 to 4, 5 to 6)
        }
    }
}