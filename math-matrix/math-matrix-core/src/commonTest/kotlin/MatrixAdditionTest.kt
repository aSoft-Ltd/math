import tz.co.asoft.Mat
import tz.co.asoft.expect
import tz.co.asoft.plus
import tz.co.asoft.toBe
import kotlin.test.Test
import kotlin.test.assertFails

class MatrixAdditionTest {
    @Test
    fun should_add_2_by_2matrix_well() {
        val m1 = Mat(
            1 to 1,
            1 to 2
        )

        val m2 = Mat(
            1 to 2,
            1 to 1
        )

        val res = Mat(
            2 to 3,
            2 to 3
        )
        expect(m1 + m2).toBe(res)
    }

    @Test
    fun should_fail_to_add_two_different_matrices() {
        assertFails {
            Mat(1 to 2) + Mat(1 to 4, 5 to 6)
        }
    }
}