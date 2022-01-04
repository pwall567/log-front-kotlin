/*
 * @(#) LoggerUtilTest.kt
 *
 * log-front-kotlin  Logging interface in Kotlin
 * Copyright (c) 2020, 2022 Peter Wall
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package net.pwall.log

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.expect
import java.time.Instant

class LoggerUtilTest {

    @Test fun `should get logger for Kotlin class`() {
        expect("net.pwall.log.LoggerUtilTest") { LoggerFactory.getDefault().getLogger(LoggerUtilTest::class).name }
    }

    @Test fun `should use alternate function signature for error with Throwable`() {
        LogList().use { logList ->
            val logger = LoggerFactory.getDefault().getLogger(LoggerUtilTest::class)
            val exception = IllegalStateException("Dummy exception")
            logger.error(exception) { "Error message" }
            val iterator = logList.iterator()
            assertTrue { iterator.hasNext() }
            val logItem = iterator.next()
            assertTrue { logItem isError "Error message" }
            expect(exception) { logItem.throwable }
        }
    }

    @Test fun `should check whether LogItem is trace`() {
        val logItem = LogItem(Instant.now(), "xyz", Level.TRACE, "Hello", null)
        assertTrue { logItem isTrace "Hello" }
        assertTrue { logItem isTrace Regex("^H") }
        assertFalse { logItem isDebug "Hello" }
        assertFalse { logItem isTrace "Goodbye" }
        assertFalse { logItem isTrace Regex("^Hello world") }
    }

    @Test fun `should check whether LogItem is debug`() {
        val logItem = LogItem(Instant.now(), "xyz", Level.DEBUG, "Hello", null)
        assertTrue { logItem isDebug "Hello" }
        assertTrue { logItem isDebug Regex("^H") }
        assertFalse { logItem isInfo "Hello" }
        assertFalse { logItem isDebug "Goodbye" }
        assertFalse { logItem isDebug Regex("^Hello world") }
    }

    @Test fun `should check whether LogItem is info`() {
        val logItem = LogItem(Instant.now(), "xyz", Level.INFO, "Hello", null)
        assertTrue { logItem isInfo "Hello" }
        assertTrue { logItem isInfo Regex("^H") }
        assertFalse { logItem isWarning "Hello" }
        assertFalse { logItem isInfo "Goodbye" }
        assertFalse { logItem isInfo Regex("^Hello world") }
    }

    @Test fun `should check whether LogItem is warning`() {
        val logItem = LogItem(Instant.now(), "xyz", Level.WARN, "Hello", null)
        assertTrue { logItem isWarning "Hello" }
        assertTrue { logItem isWarning Regex("^H") }
        assertFalse { logItem isError "Hello" }
        assertFalse { logItem isWarning "Goodbye" }
        assertFalse { logItem isWarning Regex("^Hello world") }
    }

    @Test fun `should check whether LogItem is error`() {
        val logItem = LogItem(Instant.now(), "xyz", Level.ERROR, "Hello", null)
        assertTrue { logItem isError "Hello" }
        assertTrue { logItem isError Regex("^H") }
        assertFalse { logItem isWarning  "Hello" }
        assertFalse { logItem isError "Goodbye" }
        assertFalse { logItem isError Regex("^Hello world") }
    }

}
