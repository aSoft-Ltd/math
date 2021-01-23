import tz.co.asoft.*
import kotlin.test.Test

class ComplexAdditionTest {

    @Test
    fun should_add_a_complex_and_a_decimal_numeral() {
        val z = Complex(1, 2)
        val d: Numeral<Number> = Decimal(2)
        expect(z + d).toBe(Complex(3, 2))
    }
}