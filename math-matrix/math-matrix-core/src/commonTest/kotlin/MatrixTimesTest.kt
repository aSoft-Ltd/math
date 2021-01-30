import tz.co.asoft.Mat
import tz.co.asoft.expect
import tz.co.asoft.times
import tz.co.asoft.toBe
import kotlin.test.Test

class MatrixTimesTest {
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
        expect(m1 * m2).toBe(res)
    }
}