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

package net.pwall.log.test

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertSame
import kotlin.test.assertTrue
import kotlin.test.expect
import java.time.Clock
import java.time.Instant
import java.time.ZoneOffset
import net.pwall.log.DynamicLoggerFactory
import net.pwall.log.Level
import net.pwall.log.Log
import net.pwall.log.LogItem
import net.pwall.log.Logger
import net.pwall.log.getLogger
import net.pwall.log.isDebug
import net.pwall.log.isError
import net.pwall.log.isInfo
import net.pwall.log.isTrace
import net.pwall.log.isWarning

class LoggerUtilTest {

    @Test fun `should get logger for current class`() {
        Log.setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger()) {
            expect("net.pwall.log.test.LoggerUtilTest") { name }
            expect(Log.getDefaultLoggerFactory().defaultLevel) { level }
            expect(Log.getDefaultLoggerFactory().defaultClock) { clock }
        }
        Log.setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger(Level.DEBUG)) {
            expect("net.pwall.log.test.LoggerUtilTest") { name }
            expect(Level.DEBUG) { level }
            expect(Log.getDefaultLoggerFactory().defaultClock) { clock }
        }
        Log.setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger(fixedClock)) {
            expect("net.pwall.log.test.LoggerUtilTest") { name }
            expect(Log.getDefaultLoggerFactory().defaultLevel) { level }
            assertSame(fixedClock, clock)
        }
        Log.setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger(Level.DEBUG, fixedClock)) {
            expect("net.pwall.log.test.LoggerUtilTest") { name }
            expect(Level.DEBUG) { level }
            assertSame(fixedClock, clock)
        }
    }

    @Test fun `should get logger for specified name`() {
        Log.setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger("Name")) {
            expect("Name") { name }
            expect(Log.getDefaultLoggerFactory().defaultLevel) { level }
            expect(Log.getDefaultLoggerFactory().defaultClock) { clock }
        }
        Log.setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger("Name", Level.DEBUG)) {
            expect("Name") { name }
            expect(Level.DEBUG) { level }
            expect(Log.getDefaultLoggerFactory().defaultClock) { clock }
        }
        Log.setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger("Name", fixedClock)) {
            expect("Name") { name }
            expect(Log.getDefaultLoggerFactory().defaultLevel) { level }
            assertSame(fixedClock, clock)
        }
        Log.setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger("Name", Level.TRACE, fixedClock)) {
            expect("Name") { name }
            expect(Level.TRACE) { level }
            assertSame(fixedClock, clock)
        }
    }

    @Test fun `should get logger for Java Class`() {
        Log.setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger(LoggerUtilTest::class.java)) {
            expect("net.pwall.log.test.LoggerUtilTest") { name }
            expect(Log.getDefaultLoggerFactory().defaultLevel) { level }
            expect(Log.getDefaultLoggerFactory().defaultClock) { clock }
        }
        Log.setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger(LoggerUtilTest::class.java, Level.DEBUG)) {
            expect("net.pwall.log.test.LoggerUtilTest") { name }
            expect(Level.DEBUG) { level }
            expect(Log.getDefaultLoggerFactory().defaultClock) { clock }
        }
        Log.setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger(LoggerUtilTest::class.java, fixedClock)) {
            expect("net.pwall.log.test.LoggerUtilTest") { name }
            expect(Log.getDefaultLoggerFactory().defaultLevel) { level }
            assertSame(fixedClock, clock)
        }
        Log.setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger(LoggerUtilTest::class.java, Level.DEBUG, fixedClock)) {
            expect("net.pwall.log.test.LoggerUtilTest") { name }
            expect(Level.DEBUG) { level }
            assertSame(fixedClock, clock)
        }
    }

    @Test fun `should get logger for KClass`() {
        Log.setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger(LoggerUtilTest::class)) {
            expect("net.pwall.log.test.LoggerUtilTest") { name }
            expect(Log.getDefaultLoggerFactory().defaultLevel) { level }
            expect(Log.getDefaultLoggerFactory().defaultClock) { clock }
        }
        Log.setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger(LoggerUtilTest::class, Level.DEBUG)) {
            expect("net.pwall.log.test.LoggerUtilTest") { name }
            expect(Level.DEBUG) { level }
            expect(Log.getDefaultLoggerFactory().defaultClock) { clock }
        }
        Log.setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger(LoggerUtilTest::class, fixedClock)) {
            expect("net.pwall.log.test.LoggerUtilTest") { name }
            expect(Log.getDefaultLoggerFactory().defaultLevel) { level }
            assertSame(fixedClock, clock)
        }
        Log.setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger(LoggerUtilTest::class, Level.DEBUG, fixedClock)) {
            expect("net.pwall.log.test.LoggerUtilTest") { name }
            expect(Level.DEBUG) { level }
            assertSame(fixedClock, clock)
        }
    }

    @Test fun `should get Logger from LoggerFactory by KClass`() {
        val factory = Log.getDefaultLoggerFactory()
        val logger = factory.getLogger(LoggerUtilTest::class)
        expect("net.pwall.log.test.LoggerUtilTest") { logger.name }
    }
    @Test fun `should get logger in companion object`() {
        expect("net.pwall.log.test.LoggerUtilTest") { companionObjectLogger1.name }
        expect("net.pwall.log.test.LoggerUtilTest") { companionObjectLogger2.name }
    }

    @Test fun `should check whether LogItem is trace`() {
        val logItem = LogItem(System.currentTimeMillis(), "xyz", Level.TRACE, "Hello", null)
        assertTrue { logItem isTrace "Hello" }
        assertTrue { logItem isTrace Regex("^H") }
        assertFalse { logItem isDebug "Hello" }
        assertFalse { logItem isTrace "Goodbye" }
        assertFalse { logItem isTrace Regex("^Hello world") }
    }

    @Test fun `should check whether LogItem is debug`() {
        val logItem = LogItem(System.currentTimeMillis(), "xyz", Level.DEBUG, "Hello", null)
        assertTrue { logItem isDebug "Hello" }
        assertTrue { logItem isDebug Regex("^H") }
        assertFalse { logItem isInfo "Hello" }
        assertFalse { logItem isDebug "Goodbye" }
        assertFalse { logItem isDebug Regex("^Hello world") }
    }

    @Test fun `should check whether LogItem is info`() {
        val logItem = LogItem(System.currentTimeMillis(), "xyz", Level.INFO, "Hello", null)
        assertTrue { logItem isInfo "Hello" }
        assertTrue { logItem isInfo Regex("^H") }
        assertFalse { logItem isWarning "Hello" }
        assertFalse { logItem isInfo "Goodbye" }
        assertFalse { logItem isInfo Regex("^Hello world") }
    }

    @Test fun `should check whether LogItem is warning`() {
        val logItem = LogItem(System.currentTimeMillis(), "xyz", Level.WARN, "Hello", null)
        assertTrue { logItem isWarning "Hello" }
        assertTrue { logItem isWarning Regex("^H") }
        assertFalse { logItem isError "Hello" }
        assertFalse { logItem isWarning "Goodbye" }
        assertFalse { logItem isWarning Regex("^Hello world") }
    }

    @Test fun `should check whether LogItem is error`() {
        val logItem = LogItem(System.currentTimeMillis(), "xyz", Level.ERROR, "Hello", null)
        assertTrue { logItem isError "Hello" }
        assertTrue { logItem isError Regex("^H") }
        assertFalse { logItem isWarning  "Hello" }
        assertFalse { logItem isError "Goodbye" }
        assertFalse { logItem isError Regex("^Hello world") }
    }

    companion object {
        val companionObjectLogger1: Logger = Log.getDefaultLoggerFactory().logger
        val companionObjectLogger2 = getLogger()
        val fixedClock: Clock = Clock.fixed(Instant.parse("2022-06-20T09:15:41.234Z"), ZoneOffset.UTC)
    }

}
