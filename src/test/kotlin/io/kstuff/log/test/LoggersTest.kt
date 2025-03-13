/*
 * @(#) LoggersTest.kt
 *
 * log-front-kotlin  Logging interface in Kotlin
 * Copyright (c) 2020, 2022, 2024, 2025 Peter Wall
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
import io.kstuff.test.shouldBeSameInstance

import io.kstuff.log.DynamicLoggerFactory
import io.kstuff.log.Level
import io.kstuff.log.Logger
import io.kstuff.log.getDefaultLoggerFactory
import io.kstuff.log.getLogger
import io.kstuff.log.setDefaultLoggerFactory

class LoggersTest {

    @Test fun `should get logger for current class`() {
        setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger()) {
            name shouldBe "io.kstuff.log.test.LoggersTest"
            level shouldBe getDefaultLoggerFactory().defaultLevel
            clock shouldBe getDefaultLoggerFactory().defaultClock
        }
        setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger(Level.DEBUG)) {
            name shouldBe "io.kstuff.log.test.LoggersTest"
            level shouldBe Level.DEBUG
            clock shouldBe getDefaultLoggerFactory().defaultClock
        }
        setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger(fixedClock)) {
            name shouldBe "io.kstuff.log.test.LoggersTest"
            level shouldBe getDefaultLoggerFactory().defaultLevel
            clock shouldBeSameInstance fixedClock
        }
        setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger(Level.DEBUG, fixedClock)) {
            name shouldBe "io.kstuff.log.test.LoggersTest"
            level shouldBe Level.DEBUG
            clock shouldBeSameInstance fixedClock
        }
    }

    @Test fun `should get logger for specified name`() {
        setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger("Name")) {
            name shouldBe "Name"
            level shouldBe getDefaultLoggerFactory().defaultLevel
            clock shouldBe getDefaultLoggerFactory().defaultClock
        }
        setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger("Name", Level.DEBUG)) {
            name shouldBe "Name"
            level shouldBe Level.DEBUG
            clock shouldBe getDefaultLoggerFactory().defaultClock
        }
        setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger("Name", fixedClock)) {
            name shouldBe "Name"
            level shouldBe getDefaultLoggerFactory().defaultLevel
            clock shouldBeSameInstance fixedClock
        }
        setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger("Name", Level.TRACE, fixedClock)) {
            name shouldBe "Name"
            level shouldBe Level.TRACE
            clock shouldBeSameInstance fixedClock
        }
    }

    @Test fun `should get logger for Java Class`() {
        setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger(LoggersTest::class.java)) {
            name shouldBe "io.kstuff.log.test.LoggersTest"
            level shouldBe getDefaultLoggerFactory().defaultLevel
            clock shouldBe getDefaultLoggerFactory().defaultClock
        }
        setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger(LoggersTest::class.java, Level.DEBUG)) {
            name shouldBe "io.kstuff.log.test.LoggersTest"
            level shouldBe Level.DEBUG
            clock shouldBe getDefaultLoggerFactory().defaultClock
        }
        setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger(LoggersTest::class.java, fixedClock)) {
            name shouldBe "io.kstuff.log.test.LoggersTest"
            level shouldBe getDefaultLoggerFactory().defaultLevel
            clock shouldBeSameInstance fixedClock
        }
        setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger(LoggersTest::class.java, Level.DEBUG, fixedClock)) {
            name shouldBe "io.kstuff.log.test.LoggersTest"
            level shouldBe Level.DEBUG
            clock shouldBeSameInstance fixedClock
        }
    }

    @Test fun `should get logger for KClass`() {
        setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger(LoggersTest::class)) {
            name shouldBe "io.kstuff.log.test.LoggersTest"
            level shouldBe getDefaultLoggerFactory().defaultLevel
            clock shouldBe getDefaultLoggerFactory().defaultClock
        }
        setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger(LoggersTest::class, Level.DEBUG)) {
            name shouldBe "io.kstuff.log.test.LoggersTest"
            level shouldBe Level.DEBUG
            clock shouldBe getDefaultLoggerFactory().defaultClock
        }
        setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger(LoggersTest::class, fixedClock)) {
            name shouldBe "io.kstuff.log.test.LoggersTest"
            level shouldBe getDefaultLoggerFactory().defaultLevel
            clock shouldBeSameInstance fixedClock
        }
        setDefaultLoggerFactory(DynamicLoggerFactory())
        with(getLogger(LoggersTest::class, Level.DEBUG, fixedClock)) {
            name shouldBe "io.kstuff.log.test.LoggersTest"
            level shouldBe Level.DEBUG
            clock shouldBeSameInstance fixedClock
        }
    }

    @Test fun `should get Logger from LoggerFactory by KClass`() {
        val factory = getDefaultLoggerFactory()
        val logger = factory.getLogger(LoggersTest::class)
        logger.name shouldBe "io.kstuff.log.test.LoggersTest"
    }

    @Test fun `should get logger in companion object`() {
        companionObjectLogger1.name shouldBe "io.kstuff.log.test.LoggersTest"
        companionObjectLogger2.name shouldBe "io.kstuff.log.test.LoggersTest"
    }

    companion object {
        val companionObjectLogger1: Logger = getDefaultLoggerFactory().logger
        val companionObjectLogger2 = getLogger()
        val fixedClock: Clock = Clock.fixed(Instant.parse("2022-06-20T09:15:41.234Z"), ZoneOffset.UTC)
    }

}
