import tz.co.asoft.*
import kotlin.test.Test

class ComplexAdditionTest {

    @Test
    fun should_add_a_complex_and_a_decimal_numeral() {
        val z = Complex(1, 2)
        expect(z + 2).toBe(Complex(3, 2))
    }
}