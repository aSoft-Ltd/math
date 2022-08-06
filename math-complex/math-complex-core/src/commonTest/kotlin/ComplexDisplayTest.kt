import tz.co.asoft.*
import tz.co.asoft.Complex.Companion.i
import kotlin.test.Test

class ComplexDisplayTest {

    @Test
    fun should_display_well() {
        val z = Complex(1, 2)
        expect(z.toString()).toBe("1+2i")
        expect(i.toString()).toBe("i")
        expect(Z(0, 3).toString()).toBe("3i")
    }

    @Test
    fun should_display_a_zer_complex_number_as_zero() {
        expect(Z(0, 0).toString()).toBe("0")
    }

    @Test
    fun should_display_minus_i_well() {
        expect((-i).toString()).toBe("-i")
        expect((-2 * i).toString()).toBe("-2i")
        expect(Z(0, -1).toString()).toBe("-i")
    }
}