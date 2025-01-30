/*
 * @(#) LoggerUtilTest.kt
 *
 * log-front-kotlin  Logging interface in Kotlin
 * Copyright (c) 2020, 2022, 2024, 2925 Peter Wall
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

package io.kstuff.log.test

import kotlin.test.Test

import java.time.Clock
import java.time.Instant
import java.time.ZoneOffset

import io.kstuff.test.shouldBe
import io.kstuff.test.shouldBeNonNull
import io.kstuff.test.shouldBeSameInstance
import io.kstuff.test.shouldContain
import io.kstuff.test.shouldThrow

import io.jstuff.log.DynamicLoggerFactory
import io.jstuff.log.Level
import io.jstuff.log.Log
import io.jstuff.log.LogItem
import io.jstuff.log.LogList
import io.jstuff.log.Logger

import io.kstuff.log.shouldHaveDebug
import io.kstuff.log.shouldHaveDebugMatching
import io.kstuff.log.shouldHaveError
import io.kstuff.log.shouldHaveErrorMatching
import io.kstuff.log.shouldHaveInfo
import io.kstuff.log.shouldHaveInfoMatching
import io.kstuff.log.shouldHaveTrace
import io.kstuff.log.shouldHaveTraceMatching
import io.kstuff.log.shouldHaveWarning
import io.kstuff.log.shouldHaveWarningMatching
import io.kstuff.log.getLogger
import io.kstuff.log.isDebug
import io.kstuff.log.isDebugMatching
import io.kstuff.log.isDebugContaining
import io.kstuff.log.isError
import io.kstuff.log.isErrorMatching
import io.kstuff.log.isErrorContaining
import io.kstuff.log.isInfo
import io.kstuff.log.isInfoMatching
import io.kstuff.log.isInfoContaining
import io.kstuff.log.isTrace
import io.kstuff.log.isTraceMatching
import io.kstuff.log.isTraceContaining
import io.kstuff.log.isWarning
import io.kstuff.log.isWarningMatching
import io.kstuff.log.isWarningContaining
import io.kstuff.log.shouldHaveDebugContaining
import io.kstuff.log.shouldHaveErrorContaining
import io.kstuff.log.shouldHaveInfoContaining
import io.kstuff.log.shouldHaveTraceContaining
import io.kstuff.log.shouldHaveWarningContaining
import io.kstuff.log.subList

class LoggerUtilTest {

    @Test fun `should get logger for current class`() {
        Log.setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger()) {
            name shouldBe "io.kstuff.log.test.LoggerUtilTest"
            level shouldBe Log.getDefaultLoggerFactory().defaultLevel
            clock shouldBe Log.getDefaultLoggerFactory().defaultClock
        }
        Log.setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger(Level.DEBUG)) {
            name shouldBe "io.kstuff.log.test.LoggerUtilTest"
            level shouldBe Level.DEBUG
            clock shouldBe Log.getDefaultLoggerFactory().defaultClock
        }
        Log.setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger(fixedClock)) {
            name shouldBe "io.kstuff.log.test.LoggerUtilTest"
            level shouldBe Log.getDefaultLoggerFactory().defaultLevel
            clock shouldBeSameInstance fixedClock
        }
        Log.setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger(Level.DEBUG, fixedClock)) {
            name shouldBe "io.kstuff.log.test.LoggerUtilTest"
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
            name shouldBe "io.kstuff.log.test.LoggerUtilTest"
            level shouldBe Log.getDefaultLoggerFactory().defaultLevel
            clock shouldBe Log.getDefaultLoggerFactory().defaultClock
        }
        Log.setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger(LoggerUtilTest::class.java, Level.DEBUG)) {
            name shouldBe "io.kstuff.log.test.LoggerUtilTest"
            level shouldBe Level.DEBUG
            clock shouldBe Log.getDefaultLoggerFactory().defaultClock
        }
        Log.setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger(LoggerUtilTest::class.java, fixedClock)) {
            name shouldBe "io.kstuff.log.test.LoggerUtilTest"
            level shouldBe Log.getDefaultLoggerFactory().defaultLevel
            clock shouldBeSameInstance fixedClock
        }
        Log.setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger(LoggerUtilTest::class.java, Level.DEBUG, fixedClock)) {
            name shouldBe "io.kstuff.log.test.LoggerUtilTest"
            level shouldBe Level.DEBUG
            clock shouldBeSameInstance fixedClock
        }
    }

    @Test fun `should get logger for KClass`() {
        Log.setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger(LoggerUtilTest::class)) {
            name shouldBe "io.kstuff.log.test.LoggerUtilTest"
            level shouldBe Log.getDefaultLoggerFactory().defaultLevel
            clock shouldBe Log.getDefaultLoggerFactory().defaultClock
        }
        Log.setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger(LoggerUtilTest::class, Level.DEBUG)) {
            name shouldBe "io.kstuff.log.test.LoggerUtilTest"
            level shouldBe Level.DEBUG
            clock shouldBe Log.getDefaultLoggerFactory().defaultClock
        }
        Log.setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger(LoggerUtilTest::class, fixedClock)) {
            name shouldBe "io.kstuff.log.test.LoggerUtilTest"
            level shouldBe Log.getDefaultLoggerFactory().defaultLevel
            clock shouldBeSameInstance fixedClock
        }
        Log.setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger(LoggerUtilTest::class, Level.DEBUG, fixedClock)) {
            name shouldBe "io.kstuff.log.test.LoggerUtilTest"
            level shouldBe Level.DEBUG
            clock shouldBeSameInstance fixedClock
        }
    }

    @Test fun `should get Logger from LoggerFactory by KClass`() {
        val factory = Log.getDefaultLoggerFactory()
        val logger = factory.getLogger(LoggerUtilTest::class)
        logger.name shouldBe "io.kstuff.log.test.LoggerUtilTest"
    }
    @Test fun `should get logger in companion object`() {
        companionObjectLogger1.name shouldBe "io.kstuff.log.test.LoggerUtilTest"
        companionObjectLogger2.name shouldBe "io.kstuff.log.test.LoggerUtilTest"
    }

    @Test fun `should check whether LogItem is trace`() {
        val logItem = LogItem(System.currentTimeMillis(), "xyz", Level.TRACE, "Hello", null)
        logItem isTrace "Hello" shouldBe true
        logItem isTraceContaining  "Hell" shouldBe true
        logItem isTraceMatching Regex("^H") shouldBe true
        logItem isDebug "Hello" shouldBe false
        logItem isTrace "Goodbye" shouldBe false
        logItem isTraceMatching Regex("^Hello world") shouldBe false
    }

    @Test fun `should check whether LogItem is debug`() {
        val logItem = LogItem(System.currentTimeMillis(), "xyz", Level.DEBUG, "Hello", null)
        logItem isDebug "Hello" shouldBe true
        logItem isDebugContaining  "ell" shouldBe true
        logItem isDebugMatching Regex("^H") shouldBe true
        logItem isInfo "Hello" shouldBe false
        logItem isDebug "Goodbye" shouldBe false
        logItem isDebugMatching Regex("^Hello world") shouldBe false
    }

    @Test fun `should check whether LogItem is info`() {
        val logItem = LogItem(System.currentTimeMillis(), "xyz", Level.INFO, "Hello", null)
        logItem isInfo "Hello" shouldBe true
        logItem isInfoContaining  "He" shouldBe true
        logItem isInfoMatching Regex("^H") shouldBe true
        logItem isWarning "Hello" shouldBe false
        logItem isInfo "Goodbye" shouldBe false
        logItem isInfoMatching Regex("^Hello world") shouldBe false
    }

    @Test fun `should check whether LogItem is warning`() {
        val logItem = LogItem(System.currentTimeMillis(), "xyz", Level.WARN, "Hello", null)
        logItem isWarning "Hello" shouldBe true
        logItem isWarningContaining  "lo" shouldBe true
        logItem isWarningMatching Regex("^H") shouldBe true
        logItem isError "Hello" shouldBe false
        logItem isWarning "Goodbye" shouldBe false
        logItem isWarningMatching Regex("^Hello world") shouldBe false
    }

    @Test fun `should check whether LogItem is error`() {
        val logItem = LogItem(System.currentTimeMillis(), "xyz", Level.ERROR, "Hello", null)
        logItem isError "Hello" shouldBe true
        logItem isErrorContaining  "el" shouldBe true
        logItem isErrorMatching Regex("^H") shouldBe true
        logItem isWarning  "Hello" shouldBe false
        logItem isError "Goodbye" shouldBe false
        logItem isErrorMatching Regex("^Hello world") shouldBe false
    }

    @Test fun `should check whether LogList contains TRACE item`() {
        LogList().use {  list ->
            val logger = getLogger("LogTrace", Level.TRACE)
            logger.trace { "Trace message" }
            list.shouldHaveTrace("Trace message")
            shouldThrow<AssertionError> { list.shouldHaveTrace("Wrong") }.let {
                it.message.let {  message ->
                    message.shouldBeNonNull()
                    message shouldContain "LogList does not contain TRACE Wrong"
                }
            }
        }
    }

    @Test fun `should check whether LogList contains TRACE item containing string`() {
        LogList().use {  list ->
            val logger = getLogger("LogTrace", Level.TRACE)
            logger.trace { "Trace message" }
            list.shouldHaveTraceContaining("mess")
            shouldThrow<AssertionError> { list.shouldHaveTraceContaining("Wrong") }.let {
                it.message.let {  message ->
                    message.shouldBeNonNull()
                    message shouldContain "LogList does not contain TRACE containing Wrong"
                }
            }
        }
    }

    @Test fun `should check whether LogList contains TRACE item matching Regex`() {
        LogList().use {  list ->
            val logger = getLogger("LogTrace", Level.TRACE)
            logger.trace { "Trace message" }
            list.shouldHaveTraceMatching(Regex("Trace"))
            shouldThrow<AssertionError> { list.shouldHaveTraceMatching(Regex("Wrong")) }.let {
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
            list.shouldHaveDebug("Debug message")
            shouldThrow<AssertionError> { list.shouldHaveDebug("Wrong") }.let {
                it.message.let {  message ->
                    message.shouldBeNonNull()
                    message shouldContain "LogList does not contain DEBUG Wrong"
                }
            }
        }
    }

    @Test fun `should check whether LogList contains DEBUG item containing string`() {
        LogList().use {  list ->
            val logger = getLogger("LogDebug", Level.DEBUG)
            logger.debug { "Debug message" }
            list.shouldHaveDebugContaining("bug")
            shouldThrow<AssertionError> { list.shouldHaveDebugContaining("Wrong") }.let {
                it.message.let {  message ->
                    message.shouldBeNonNull()
                    message shouldContain "LogList does not contain DEBUG containing Wrong"
                }
            }
        }
    }

    @Test fun `should check whether LogList contains DEBUG item matching Regex`() {
        LogList().use {  list ->
            val logger = getLogger("LogDebug", Level.DEBUG)
            logger.debug { "Debug message" }
            list.shouldHaveDebugMatching(Regex("Debug"))
            shouldThrow<AssertionError> { list.shouldHaveDebugMatching(Regex("Wrong")) }.let {
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
            list.shouldHaveInfo("Message")
            shouldThrow<AssertionError> { list.shouldHaveInfo("Wrong") }.let {
                it.message.let {  message ->
                    message.shouldBeNonNull()
                    message shouldContain "LogList does not contain INFO Wrong"
                }
            }
        }
    }

    @Test fun `should check whether LogList contains INFO item containing string`() {
        LogList().use {  list ->
            companionObjectLogger1.info { "Message" }
            list.shouldHaveInfoContaining("sage")
            shouldThrow<AssertionError> { list.shouldHaveInfoContaining("Wrong") }.let {
                it.message.let {  message ->
                    message.shouldBeNonNull()
                    message shouldContain "LogList does not contain INFO containing Wrong"
                }
            }
        }
    }

    @Test fun `should check whether LogList contains INFO item matching Regex`() {
        LogList().use {  list ->
            companionObjectLogger1.info { "Message" }
            list.shouldHaveInfoMatching(Regex("Mess"))
            shouldThrow<AssertionError> { list.shouldHaveInfoMatching(Regex("Wrong")) }.let {
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
            list.shouldHaveWarning("Warning message")
            shouldThrow<AssertionError> { list.shouldHaveWarning("Wrong") }.let {
                it.message.let {  message ->
                    message.shouldBeNonNull()
                    message shouldContain "LogList does not contain WARN Wrong"
                }
            }
        }
    }

    @Test fun `should check whether LogList contains WARN item containing string`() {
        LogList().use {  list ->
            companionObjectLogger1.warn { "Warning message" }
            list.shouldHaveWarningContaining("age")
            shouldThrow<AssertionError> { list.shouldHaveWarningContaining("Wrong") }.let {
                it.message.let {  message ->
                    message.shouldBeNonNull()
                    message shouldContain "LogList does not contain WARN containing Wrong"
                }
            }
        }
    }

    @Test fun `should check whether LogList contains WARN item matching Regex`() {
        LogList().use {  list ->
            companionObjectLogger1.warn { "Warning message" }
            list.shouldHaveWarningMatching(Regex("Warning"))
            shouldThrow<AssertionError> { list.shouldHaveWarningMatching(Regex("Wrong")) }.let {
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
            list.shouldHaveError("Error message")
            shouldThrow<AssertionError> { list.shouldHaveError("Wrong") }.let {
                it.message.let {  message ->
                    message.shouldBeNonNull()
                    message shouldContain "LogList does not contain ERROR Wrong"
                }
            }
        }
    }

    @Test fun `should check whether LogList contains ERROR item containing string`() {
        LogList().use {  list ->
            companionObjectLogger1.error { "Error message" }
            list.shouldHaveErrorContaining("Err")
            shouldThrow<AssertionError> { list.shouldHaveErrorContaining("Wrong") }.let {
                it.message.let {  message ->
                    message.shouldBeNonNull()
                    message shouldContain "LogList does not contain ERROR containing Wrong"
                }
            }
        }
    }

    @Test fun `should check whether LogList contains ERROR item matching Regex`() {
        LogList().use {  list ->
            companionObjectLogger1.error { "Error message" }
            list.shouldHaveErrorMatching(Regex("Error"))
            shouldThrow<AssertionError> { list.shouldHaveErrorMatching(Regex("Wrong")) }.let {
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
            list.subList("TestLog").shouldHaveInfo("Message")
        }
    }

    companion object {
        val companionObjectLogger1: Logger = Log.getDefaultLoggerFactory().logger
        val companionObjectLogger2 = getLogger()
        val fixedClock: Clock = Clock.fixed(Instant.parse("2022-06-20T09:15:41.234Z"), ZoneOffset.UTC)
    }

}
