import tz.co.asoft.Mat
import tz.co.asoft.R
import tz.co.asoft.expect
import tz.co.asoft.toBe
import kotlin.test.Test
import kotlin.test.assertFails

class MatrixSyntaxTest {
    @Test
    fun should_instantiate_a_two_by_two_matrix_easily() {
        val m = Mat(
            1 to 2,
            3 to 4
        )
        expect(m[1][1]).toBe(1)
        expect(m[1][2]).toBe(2)
        expect(m[2][1]).toBe(3)
        expect(m[2][2]).toBe(4)
    }

    @Test
    fun should_instantiate_any_number_of_rows_and_columns() {
        val m = Mat(
            R(1, 2, 3, 4),
            R(2, 3, 4, 5)
        )
        expect(m[1][2]).toBe(2)
        expect(m[2][3]).toBe(4)
    }

    @Test
    fun should_fail_to_instantiate() {
        assertFails {
            Mat(
                R(1, 2),
                R(2, 3, 4)
            )
        }
    }
}