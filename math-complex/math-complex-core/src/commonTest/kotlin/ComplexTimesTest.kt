import tz.co.asoft.*
import tz.co.asoft.Complex.Companion.i
import kotlin.test.Test

class ComplexTimesTest {

    @Test
    fun should_add_a_complex_and_a_decimal_numeral() {
        expect((2 * i).toString()).toBe("2i")
        expect((i * i).toString()).toBe("-1")
    }
}