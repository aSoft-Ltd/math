import tz.co.asoft.Mat
import tz.co.asoft.expect
import tz.co.asoft.toBe
import tz.co.asoft.toBeEqualTo
import kotlin.test.Test

class MatrixEqualityTest {
    @Test
    fun instances_with_same_values_should_be_considered_equal() {
        val m1 = Mat(
            1 to 2,
            3 to 4
        )

        val m2 = Mat(
            1 to 2,
            3 to 4
        )

        expect(m1).toBeEqualTo(m2)
    }
}