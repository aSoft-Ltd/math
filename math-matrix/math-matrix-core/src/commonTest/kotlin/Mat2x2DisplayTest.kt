import tz.co.asoft.Mat2x2
import kotlin.test.Test

class Mat2x2DisplayTest {
    @Test
    fun should_print_a_2_by_2_matrix() {
        val m = Mat2x2(1, 2, 3, 4)
        println(m.toStringLines())
    }
}