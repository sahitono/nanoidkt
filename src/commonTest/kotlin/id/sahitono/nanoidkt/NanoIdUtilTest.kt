package id.sahitono.nanoidkt

import org.junit.jupiter.api.Test

class NanoIdUtilTest {
    @Test
    fun testDefault() {
        check(randomNanoId().length == 21)
    }

    @Test
    fun testDifferentSize() {
        check(randomNanoId(size = 10).length == 10)
    }
}