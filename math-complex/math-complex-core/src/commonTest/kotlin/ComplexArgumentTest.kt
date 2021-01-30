import tz.co.asoft.*
import tz.co.asoft.Complex.Companion.i
import kotlin.math.PI
import kotlin.test.Test

class ComplexArgumentTest {

    @Test
    fun have_a_magnitude_of_45_deg() {
        expect(Z(1, 1).arg()).toBe(PI / 4)
        expect(i.arg()).toBe(PI / 2)
    }
}