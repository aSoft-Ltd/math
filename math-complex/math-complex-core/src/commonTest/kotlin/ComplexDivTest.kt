import tz.co.asoft.*
import tz.co.asoft.Complex.Companion.i
import kotlin.test.Test

class ComplexDivTest {

    @Test
    fun should_divide_two_complex_numbers() {
        expect((i / i).toString()).toBe("1")
        expect((1 / i).toString()).toBe("-i")
    }
}