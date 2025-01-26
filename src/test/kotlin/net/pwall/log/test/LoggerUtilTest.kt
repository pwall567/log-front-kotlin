/*
 * @(#) LoggerUtilTest.kt
 *
 * log-front-kotlin  Logging interface in Kotlin
 * Copyright (c) 2020, 2022, 2024 Peter Wall
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

import java.time.Clock
import java.time.Instant
import java.time.ZoneOffset

import io.kstuff.test.shouldBe
import io.kstuff.test.shouldBeNonNull
import io.kstuff.test.shouldBeSameInstance
import io.kstuff.test.shouldContain
import io.kstuff.test.shouldThrow

import net.pwall.log.DynamicLoggerFactory
import net.pwall.log.Level
import net.pwall.log.Log
import net.pwall.log.LogItem
import net.pwall.log.LogList
import net.pwall.log.Logger
import net.pwall.log.assertHasDebug
import net.pwall.log.assertHasError
import net.pwall.log.assertHasInfo
import net.pwall.log.assertHasTrace
import net.pwall.log.assertHasWarning
import net.pwall.log.getLogger
import net.pwall.log.isDebug
import net.pwall.log.isError
import net.pwall.log.isInfo
import net.pwall.log.isTrace
import net.pwall.log.isWarning
import net.pwall.log.subList

class LoggerUtilTest {

    @Test fun `should get logger for current class`() {
        Log.setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger()) {
            name shouldBe "net.pwall.log.test.LoggerUtilTest"
            level shouldBe Log.getDefaultLoggerFactory().defaultLevel
            clock shouldBe Log.getDefaultLoggerFactory().defaultClock
        }
        Log.setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger(Level.DEBUG)) {
            name shouldBe "net.pwall.log.test.LoggerUtilTest"
            level shouldBe Level.DEBUG
            clock shouldBe Log.getDefaultLoggerFactory().defaultClock
        }
        Log.setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger(fixedClock)) {
            name shouldBe "net.pwall.log.test.LoggerUtilTest"
            level shouldBe Log.getDefaultLoggerFactory().defaultLevel
            clock shouldBeSameInstance fixedClock
        }
        Log.setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger(Level.DEBUG, fixedClock)) {
            name shouldBe "net.pwall.log.test.LoggerUtilTest"
            level shouldBe Level.DEBUG
            clock shouldBeSameInstance fixedClock
        }
    }

    @Test fun `should get logger for specified name`() {
        Log.setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger("Name")) {
            name shouldBe "Name"
            level shouldBe Log.getDefaultLoggerFactory().defaultLevel
            clock shouldBe Log.getDefaultLoggerFactory().defaultClock
        }
        Log.setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger("Name", Level.DEBUG)) {
            name shouldBe "Name"
            level shouldBe Level.DEBUG
            clock shouldBe Log.getDefaultLoggerFactory().defaultClock
        }
        Log.setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger("Name", fixedClock)) {
            name shouldBe "Name"
            level shouldBe Log.getDefaultLoggerFactory().defaultLevel
            clock shouldBeSameInstance fixedClock
        }
        Log.setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger("Name", Level.TRACE, fixedClock)) {
            name shouldBe "Name"
            level shouldBe Level.TRACE
            clock shouldBeSameInstance fixedClock
        }
    }

    @Test fun `should get logger for Java Class`() {
        Log.setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger(LoggerUtilTest::class.java)) {
            name shouldBe "net.pwall.log.test.LoggerUtilTest"
            level shouldBe Log.getDefaultLoggerFactory().defaultLevel
            clock shouldBe Log.getDefaultLoggerFactory().defaultClock
        }
        Log.setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger(LoggerUtilTest::class.java, Level.DEBUG)) {
            name shouldBe "net.pwall.log.test.LoggerUtilTest"
            level shouldBe Level.DEBUG
            clock shouldBe Log.getDefaultLoggerFactory().defaultClock
        }
        Log.setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger(LoggerUtilTest::class.java, fixedClock)) {
            name shouldBe "net.pwall.log.test.LoggerUtilTest"
            level shouldBe Log.getDefaultLoggerFactory().defaultLevel
            clock shouldBeSameInstance fixedClock
        }
        Log.setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger(LoggerUtilTest::class.java, Level.DEBUG, fixedClock)) {
            name shouldBe "net.pwall.log.test.LoggerUtilTest"
            level shouldBe Level.DEBUG
            clock shouldBeSameInstance fixedClock
        }
    }

    @Test fun `should get logger for KClass`() {
        Log.setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger(LoggerUtilTest::class)) {
            name shouldBe "net.pwall.log.test.LoggerUtilTest"
            level shouldBe Log.getDefaultLoggerFactory().defaultLevel
            clock shouldBe Log.getDefaultLoggerFactory().defaultClock
        }
        Log.setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger(LoggerUtilTest::class, Level.DEBUG)) {
            name shouldBe "net.pwall.log.test.LoggerUtilTest"
            level shouldBe Level.DEBUG
            clock shouldBe Log.getDefaultLoggerFactory().defaultClock
        }
        Log.setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger(LoggerUtilTest::class, fixedClock)) {
            name shouldBe "net.pwall.log.test.LoggerUtilTest"
            level shouldBe Log.getDefaultLoggerFactory().defaultLevel
            clock shouldBeSameInstance fixedClock
        }
        Log.setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger(LoggerUtilTest::class, Level.DEBUG, fixedClock)) {
            name shouldBe "net.pwall.log.test.LoggerUtilTest"
            level shouldBe Level.DEBUG
            clock shouldBeSameInstance fixedClock
        }
    }

    @Test fun `should get Logger from LoggerFactory by KClass`() {
        val factory = Log.getDefaultLoggerFactory()
        val logger = factory.getLogger(LoggerUtilTest::class)
        logger.name shouldBe "net.pwall.log.test.LoggerUtilTest"
    }
    @Test fun `should get logger in companion object`() {
        companionObjectLogger1.name shouldBe "net.pwall.log.test.LoggerUtilTest"
        companionObjectLogger2.name shouldBe "net.pwall.log.test.LoggerUtilTest"
    }

    @Test fun `should check whether LogItem is trace`() {
        val logItem = LogItem(System.currentTimeMillis(), "xyz", Level.TRACE, "Hello", null)
        logItem isTrace "Hello" shouldBe true
        logItem isTrace Regex("^H") shouldBe true
        logItem isDebug "Hello" shouldBe false
        logItem isTrace "Goodbye" shouldBe false
        logItem isTrace Regex("^Hello world") shouldBe false
    }

    @Test fun `should check whether LogItem is debug`() {
        val logItem = LogItem(System.currentTimeMillis(), "xyz", Level.DEBUG, "Hello", null)
        logItem isDebug "Hello" shouldBe true
        logItem isDebug Regex("^H") shouldBe true
        logItem isInfo "Hello" shouldBe false
        logItem isDebug "Goodbye" shouldBe false
        logItem isDebug Regex("^Hello world") shouldBe false
    }

    @Test fun `should check whether LogItem is info`() {
        val logItem = LogItem(System.currentTimeMillis(), "xyz", Level.INFO, "Hello", null)
        logItem isInfo "Hello" shouldBe true
        logItem isInfo Regex("^H") shouldBe true
        logItem isWarning "Hello" shouldBe false
        logItem isInfo "Goodbye" shouldBe false
        logItem isInfo Regex("^Hello world") shouldBe false
    }

    @Test fun `should check whether LogItem is warning`() {
        val logItem = LogItem(System.currentTimeMillis(), "xyz", Level.WARN, "Hello", null)
        logItem isWarning "Hello" shouldBe true
        logItem isWarning Regex("^H") shouldBe true
        logItem isError "Hello" shouldBe false
        logItem isWarning "Goodbye" shouldBe false
        logItem isWarning Regex("^Hello world") shouldBe false
    }

    @Test fun `should check whether LogItem is error`() {
        val logItem = LogItem(System.currentTimeMillis(), "xyz", Level.ERROR, "Hello", null)
        logItem isError "Hello" shouldBe true
        logItem isError Regex("^H") shouldBe true
        logItem isWarning  "Hello" shouldBe false
        logItem isError "Goodbye" shouldBe false
        logItem isError Regex("^Hello world") shouldBe false
    }

    @Test fun `should check whether LogList contains TRACE item`() {
        LogList().use {  list ->
            val logger = getLogger("LogTrace", Level.TRACE)
            logger.trace { "Trace message" }
            list.assertHasTrace("Trace message")
            shouldThrow<AssertionError> { list.assertHasTrace("Wrong") }.let {
                it.message.let {  message ->
                    message.shouldBeNonNull()
                    message shouldContain "LogList does not contain TRACE Wrong"
                }
            }
        }
    }

    @Test fun `should check whether LogList contains TRACE item matching Regex`() {
        LogList().use {  list ->
            val logger = getLogger("LogTrace", Level.TRACE)
            logger.trace { "Trace message" }
            list.assertHasTrace(Regex("Trace"))
            shouldThrow<AssertionError> { list.assertHasTrace(Regex("Wrong")) }.let {
                it.message.let {  message ->
                    message.shouldBeNonNull()
                    message shouldContain "LogList does not contain TRACE Wrong"
                }
            }
        }
    }

    @Test fun `should check whether LogList contains DEBUG item`() {
        LogList().use {  list ->
            val logger = getLogger("LogDebug", Level.DEBUG)
            logger.debug { "Debug message" }
            list.assertHasDebug("Debug message")
            shouldThrow<AssertionError> { list.assertHasDebug("Wrong") }.let {
                it.message.let {  message ->
                    message.shouldBeNonNull()
                    message shouldContain "LogList does not contain DEBUG Wrong"
                }
            }
        }
    }

    @Test fun `should check whether LogList contains DEBUG item matching Regex`() {
        LogList().use {  list ->
            val logger = getLogger("LogDebug", Level.DEBUG)
            logger.debug { "Debug message" }
            list.assertHasDebug(Regex("Debug"))
            shouldThrow<AssertionError> { list.assertHasDebug(Regex("Wrong")) }.let {
                it.message.let {  message ->
                    message.shouldBeNonNull()
                    message shouldContain "LogList does not contain DEBUG Wrong"
                }
            }
        }
    }

    @Test fun `should check whether LogList contains INFO item`() {
        LogList().use {  list ->
            companionObjectLogger1.info { "Message" }
            list.assertHasInfo("Message")
            shouldThrow<AssertionError> { list.assertHasInfo("Wrong") }.let {
                it.message.let {  message ->
                    message.shouldBeNonNull()
                    message shouldContain "LogList does not contain INFO Wrong"
                }
            }
        }
    }

    @Test fun `should check whether LogList contains INFO item matching Regex`() {
        LogList().use {  list ->
            companionObjectLogger1.info { "Message" }
            list.assertHasInfo(Regex("Mess"))
            shouldThrow<AssertionError> { list.assertHasInfo(Regex("Wrong")) }.let {
                it.message.let {  message ->
                    message.shouldBeNonNull()
                    message shouldContain "LogList does not contain INFO Wrong"
                }
            }
        }
    }

    @Test fun `should check whether LogList contains WARN item`() {
        LogList().use {  list ->
            companionObjectLogger1.warn { "Warning message" }
            list.assertHasWarning("Warning message")
            shouldThrow<AssertionError> { list.assertHasWarning("Wrong") }.let {
                it.message.let {  message ->
                    message.shouldBeNonNull()
                    message shouldContain "LogList does not contain WARN Wrong"
                }
            }
        }
    }

    @Test fun `should check whether LogList contains WARN item matching Regex`() {
        LogList().use {  list ->
            companionObjectLogger1.warn { "Warning message" }
            list.assertHasWarning(Regex("Warning"))
            shouldThrow<AssertionError> { list.assertHasWarning(Regex("Wrong")) }.let {
                it.message.let {  message ->
                    message.shouldBeNonNull()
                    message shouldContain "LogList does not contain WARN Wrong"
                }
            }
        }
    }

    @Test fun `should check whether LogList contains ERROR item`() {
        LogList().use {  list ->
            companionObjectLogger1.error { "Error message" }
            list.assertHasError("Error message")
            shouldThrow<AssertionError> { list.assertHasError("Wrong") }.let {
                it.message.let {  message ->
                    message.shouldBeNonNull()
                    message shouldContain "LogList does not contain ERROR Wrong"
                }
            }
        }
    }

    @Test fun `should check whether LogList contains ERROR item matching Regex`() {
        LogList().use {  list ->
            companionObjectLogger1.error { "Error message" }
            list.assertHasError(Regex("Error"))
            shouldThrow<AssertionError> { list.assertHasError(Regex("Wrong")) }.let {
                it.message.let {  message ->
                    message.shouldBeNonNull()
                    message shouldContain "LogList does not contain ERROR Wrong"
                }
            }
        }
    }

    @Test fun `should get sub-list of LogList`() {
        LogList().use {  list ->
            val logger = getLogger("TestLog")
            logger.info { "Message" }
            list.subList("TestLog").assertHasInfo("Message")
        }
    }

    companion object {
        val companionObjectLogger1: Logger = Log.getDefaultLoggerFactory().logger
        val companionObjectLogger2 = getLogger()
        val fixedClock: Clock = Clock.fixed(Instant.parse("2022-06-20T09:15:41.234Z"), ZoneOffset.UTC)
    }

}
