import tz.co.asoft.expect
import tz.co.asoft.signString
import tz.co.asoft.toBe
import kotlin.test.Test

class SignTest {
    @Test
    fun should_return_sign() {
        expect((-2.6).signString()).toBe("-")
        expect((-0.0).signString()).toBe("+")
        expect(0.signString()).toBe("+")
    }
}