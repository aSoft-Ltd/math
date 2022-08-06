import expect.expect
import math.toReadableString
import kotlin.test.Test

class ReadableStringTest {
    @Test
    fun should_remove_trailing_zero() {
        expect(2.0.toReadableString()).toBe("2")
        expect(2f.toReadableString()).toBe("2")
        expect((-4.0).toReadableString()).toBe("-4")
        expect(0.0.toReadableString()).toBe("0")
        expect(1.2.toReadableString()).toBe("1.2")
    }
}