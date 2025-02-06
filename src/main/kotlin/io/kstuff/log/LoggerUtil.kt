/*
 * @(#) LoggerUtil.kt
 *
 * log-front-kotlin  Logging interface in Kotlin
 * Copyright (c) 2020, 2021, 2022, 2024, 2025 Peter Wall
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

package io.kstuff.log

import kotlin.reflect.KClass

import java.io.PrintStream
import java.time.Clock

import io.jstuff.log.Log

/**
 * The dynamic [LoggerFactory], which tries to create a [Logger] as follows:
 *
 * 1. If `slf4j` is available on the class path, create an [Slf4jLogger]
 * 2. If the system property `java.util.logging.config.file` is present, or if the file `logging.properties` is present
 * as a resource, create a [JavaLogger]
 * 3. Otherwise, create a [ConsoleLogger]
 */
typealias DynamicLoggerFactory = io.jstuff.log.DynamicLoggerFactory

/**
 * A [Logger] that outputs to a [PrintStream], usually `stdout` or `stderr`.  This has largely been superseded by the
 * [FormattingLogger], and may be deprecated in future.
 */
typealias ConsoleLogger = io.jstuff.log.ConsoleLogger

/**
 * A [Logger] implementation that uses a supplied [LogFormatter], outputting to the supplied [LogAppender].
 */
typealias FormattingLogger<F, A> = io.jstuff.log.FormattingLogger<F, A>

/**
 * A [LoggerFactory] that creates [FormattingLogger] objects.
 */
typealias FormattingLoggerFactory<F, A> = io.jstuff.log.FormattingLoggerFactory<F, A>

/**
 * A [Logger] that uses the `java.util.logging` framework.
 */
typealias JavaLogger = io.jstuff.log.JavaLogger

/**
 * The logging level.
 */
typealias Level = io.jstuff.log.Level

/**
 * The main [Logger] interface.
 */
typealias Logger = io.jstuff.log.Logger

/**
 * The `LoggerFactory` supplies a [Logger] of a particular type.
 */
typealias LoggerFactory<L> = io.jstuff.log.LoggerFactory<L>

/**
 * Log Appender interface.
 */
typealias LogAppender<F> = io.jstuff.log.LogAppender<F>

/**
 * Log Formatter interface.
 */
typealias LogFormatter = io.jstuff.log.LogFormatter

/**
 * A log item, as used by the [LogList] class.  This is an immutable object.
 */
typealias LogItem = io.jstuff.log.LogItem

/**
 * An implementation of [LogListener] that stores log items in a list.
 */
typealias LogList = io.jstuff.log.LogList

/**
 * The [LogListener] class is the base class for an object that is called for every log event.  It is intended to be
 * used only in unit tests.
 *
 * The constructor adds the object to a static list of listeners; the [Logger] classes will check for the presence of
 * listeners and invoke them as required.
 */
typealias LogListener = io.jstuff.log.LogListener

/**
 * A [Logger] that outputs to `slf4j`.  To avoid a transitive dependency on that package, all references are made by
 * means of reflection-based calls.
 */
typealias Slf4jLogger = io.jstuff.log.Slf4jLogger

/**
 * Get the default [LoggerFactory] (initially a [DynamicLoggerFactory])
 */
fun getDefaultLoggerFactory(): LoggerFactory<*> = Log.getDefaultLoggerFactory()

/**
 * Set the default logger factory.
 */
fun setDefaultLoggerFactory(loggerFactory: LoggerFactory<*>) {
    Log.setDefaultLoggerFactory(loggerFactory)
}

/**
 * Get a [Logger] from the default [LoggerFactory], using the name of the calling class and the default level and clock.
 */
fun getLogger(): Logger = Log.getLogger(LoggerFactory.callerInfo().className)

/**
 * Get a [Logger] from the default [LoggerFactory], using the name of the calling class, the specified level and the
 * default clock.
 */
fun getLogger(level: Level): Logger = Log.getLogger(LoggerFactory.callerInfo().className, level)

/**
 * Get a [Logger] from the default [LoggerFactory], using the name of the calling class, the default level and the
 * specified clock.
 */
fun getLogger(clock: Clock): Logger = Log.getLogger(LoggerFactory.callerInfo().className, clock)

/**
 * Get a [Logger] from the default [LoggerFactory], using the name of the calling class and the specified level and
 * clock.
 */
fun getLogger(level: Level, clock: Clock): Logger = Log.getLogger(LoggerFactory.callerInfo().className, level, clock)

/**
 * Get a [Logger] from the default [LoggerFactory], using the specified name and the default level and clock.
 */
fun getLogger(name: String): Logger = Log.getLogger(name)

/**
 * Get a [Logger] from the default [LoggerFactory], using the specified name and level and the default clock.
 */
fun getLogger(name: String, level: Level): Logger = Log.getLogger(name, level)

/**
 * Get a [Logger] from the default [LoggerFactory], using the specified name and clock and the default level.
 */
fun getLogger(name: String, clock: Clock): Logger = Log.getLogger(name, clock)

/**
 * Get a [Logger] from the default [LoggerFactory], using the specified name, level and clock.
 */
fun getLogger(name: String, level: Level, clock: Clock): Logger = Log.getLogger(name, level, clock)

/**
 * Get a [Logger] from the default [LoggerFactory], using the specified Java [Class] name and the default level and
 * clock.
 */
fun getLogger(javaClass: Class<*>): Logger = Log.getLogger(javaClass.name)

/**
 * Get a [Logger] from the default [LoggerFactory], using the specified Java [Class] name and level and the default
 * clock.
 */
fun getLogger(javaClass: Class<*>, level: Level): Logger = Log.getLogger(javaClass.name, level)

/**
 * Get a [Logger] from the default [LoggerFactory], using the specified Java [Class] name and clock and the default
 * level.
 */
fun getLogger(javaClass: Class<*>, clock: Clock): Logger = Log.getLogger(javaClass.name, clock)

/**
 * Get a [Logger] from the default [LoggerFactory], using the specified Java [Class] name, level and clock.
 */
fun getLogger(javaClass: Class<*>, level: Level, clock: Clock): Logger = Log.getLogger(javaClass.name, level, clock)

/**
 * Get a [Logger] from the default [LoggerFactory], using the specified [KClass] name and the default level and clock.
 */
fun getLogger(kClass: KClass<*>): Logger = Log.getLogger(kClass.qualifiedName)

/**
 * Get a [Logger] from the default [LoggerFactory], using the specified [KClass] name and level and the default clock.
 */
fun getLogger(kClass: KClass<*>, level: Level): Logger = Log.getLogger(kClass.qualifiedName, level)

/**
 * Get a [Logger] from the default [LoggerFactory], using the specified [KClass] name and clock and the default level.
 */
fun getLogger(kClass: KClass<*>, clock: Clock): Logger = Log.getLogger(kClass.qualifiedName, clock)

/**
 * Get a [Logger] from the default [LoggerFactory], using the specified [KClass] name, level and clock.
 */
fun getLogger(kClass: KClass<*>, level: Level, clock: Clock): Logger = Log.getLogger(kClass.qualifiedName, level, clock)

/**
 * Get a [Logger] for a Kotlin [KClass], using the optional [Level] and [Clock].  (Kotlin equivalent of same function
 * taking a Java [Class]).
 */
fun <L : Logger> LoggerFactory<L>.getLogger(
    kClass: KClass<*>,
    level: Level = defaultLevel,
    clock: Clock = defaultClock,
): L = getLogger(kClass.qualifiedName, level, clock)

/**
 * Test whether a [LogItem] represents a message with level `TRACE` and the specified text.
 */
infix fun LogItem.isTrace(text: String): Boolean = level == Level.TRACE && message == text

/**
 * Test whether a [LogItem] represents a message with level `TRACE` containing the specified text.
 */
infix fun LogItem.isTraceContaining(text: String): Boolean = level == Level.TRACE && message.toString().contains(text)

/**
 * Test whether a [LogItem] represents a message with level `TRACE` and the specified text.
 */
infix fun LogItem.isTraceMatching(regex: Regex): Boolean =
        level == Level.TRACE && regex.containsMatchIn(message.toString())

/**
 * Test whether a [LogItem] represents a message with level `DEBUG` and the specified text.
 */
infix fun LogItem.isDebug(text: String): Boolean = level == Level.DEBUG && message == text

/**
 * Test whether a [LogItem] represents a message with level `DEBUG` containing the specified text.
 */
infix fun LogItem.isDebugContaining(text: String): Boolean = level == Level.DEBUG && message.toString().contains(text)

/**
 * Test whether a [LogItem] represents a message with level `DEBUG` and the specified text.
 */
infix fun LogItem.isDebugMatching(regex: Regex): Boolean =
        level == Level.DEBUG && regex.containsMatchIn(message.toString())

/**
 * Test whether a [LogItem] represents a message with level `INFO` and the specified text.
 */
infix fun LogItem.isInfo(text: String): Boolean = level == Level.INFO && message == text

/**
 * Test whether a [LogItem] represents a message with level `INFO` containing the specified text.
 */
infix fun LogItem.isInfoContaining(text: String): Boolean = level == Level.INFO && message.toString().contains(text)

/**
 * Test whether a [LogItem] represents a message with level `INFO` and text that matches the given [Regex].
 */
infix fun LogItem.isInfoMatching(regex: Regex): Boolean =
        level == Level.INFO && regex.containsMatchIn(message.toString())

/**
 * Test whether a [LogItem] represents a message with level `WARN` and the specified text.
 */
infix fun LogItem.isWarning(text: String): Boolean = level == Level.WARN && message == text

/**
 * Test whether a [LogItem] represents a message with level `WARN` containing the specified text.
 */
infix fun LogItem.isWarningContaining(text: String): Boolean = level == Level.WARN && message.toString().contains(text)

/**
 * Test whether a [LogItem] represents a message with level `WARN` and text that matches the given [Regex].
 */
infix fun LogItem.isWarningMatching(regex: Regex): Boolean =
        level == Level.WARN && regex.containsMatchIn(message.toString())

/**
 * Test whether a [LogItem] represents a message with level `ERROR` and the specified text.
 */
infix fun LogItem.isError(text: String): Boolean = level == Level.ERROR && message == text

/**
 * Test whether a [LogItem] represents a message with level `ERROR` and the specified text.
 */
infix fun LogItem.isErrorContaining(text: String): Boolean = level == Level.ERROR && message.toString().contains(text)

/**
 * Test whether a [LogItem] represents a message with level `ERROR` and text that matches the given [Regex].
 */
infix fun LogItem.isErrorMatching(regex: Regex): Boolean =
        level == Level.ERROR && regex.containsMatchIn(message.toString())

/**
 * Filter list by name.
 */
fun Iterable<LogItem>.subList(name: String): Iterable<LogItem> = filter { it.name == name }

/**
 * Test whether a collection of [LogItem] has a `TRACE` entry with the specified text.
 */
infix fun Iterable<LogItem>.shouldHaveTrace(text: String) {
    if (!any { it isTrace text }) {
        throw AssertionError("LogList does not contain TRACE $text\nLog lines:\n${joinToString(separator = "\n")}")
    }
}

/**
 * Test whether a collection of [LogItem] has a `TRACE` entry containing the specified text.
 */
infix fun Iterable<LogItem>.shouldHaveTraceContaining(text: String) {
    if (!any { it isTraceContaining text }) {
        throw AssertionError(
            "LogList does not contain TRACE containing $text\nLog lines:\n${joinToString(separator = "\n")}"
        )
    }
}

/**
 * Test whether a collection of [LogItem] has a `TRACE` entry matching the specified [Regex].
 */
infix fun Iterable<LogItem>.shouldHaveTraceMatching(regex: Regex) {
    if (!any { it isTraceMatching regex }) {
        throw AssertionError("LogList does not contain TRACE $regex\nLog lines:\n${joinToString(separator = "\n")}")
    }
}

/**
 * Test whether a collection of [LogItem] has a `DEBUG` entry with the specified text.
 */
infix fun Iterable<LogItem>.shouldHaveDebug(text: String) {
    if (!any { it isDebug text }) {
        throw AssertionError("LogList does not contain DEBUG $text\nLog lines:\n${joinToString(separator = "\n")}")
    }
}

/**
 * Test whether a collection of [LogItem] has a `DEBUG` entry containing the specified text.
 */
infix fun Iterable<LogItem>.shouldHaveDebugContaining(text: String) {
    if (!any { it isDebugContaining  text }) {
        throw AssertionError(
            "LogList does not contain DEBUG containing $text\nLog lines:\n${joinToString(separator = "\n")}"
        )
    }
}

/**
 * Test whether a collection of [LogItem] has a `DEBUG` entry matching the specified [Regex].
 */
infix fun Iterable<LogItem>.shouldHaveDebugMatching(regex: Regex) {
    if (!any { it isDebugMatching regex }) {
        throw AssertionError("LogList does not contain DEBUG $regex\nLog lines:\n${joinToString(separator = "\n")}")
    }
}

/**
 * Test whether a collection of [LogItem] has an `INFO` entry with the specified text.
 */
infix fun Iterable<LogItem>.shouldHaveInfo(text: String) {
    if (!any { it isInfo text }) {
        throw AssertionError("LogList does not contain INFO $text\nLog lines:\n${joinToString(separator = "\n")}")
    }
}

/**
 * Test whether a collection of [LogItem] has an `INFO` entry containing the specified text.
 */
infix fun Iterable<LogItem>.shouldHaveInfoContaining(text: String) {
    if (!any { it isInfoContaining text }) {
        throw AssertionError(
            "LogList does not contain INFO containing $text\nLog lines:\n${joinToString(separator = "\n")}"
        )
    }
}

/**
 * Test whether a collection of [LogItem] has an `INFO` entry matching the specified [Regex].
 */
infix fun Iterable<LogItem>.shouldHaveInfoMatching(regex: Regex) {
    if (!any { it isInfoMatching regex }) {
        throw AssertionError("LogList does not contain INFO $regex\nLog lines:\n${joinToString(separator = "\n")}")
    }
}

/**
 * Test whether a collection of [LogItem] has a `WARN` entry with the specified text.
 */
infix fun Iterable<LogItem>.shouldHaveWarning(text: String) {
    if (!any { it isWarning text }) {
        throw AssertionError("LogList does not contain WARN $text\nLog lines:\n${joinToString(separator = "\n")}")
    }
}

/**
 * Test whether a collection of [LogItem] has a `WARN` entry containing the specified text.
 */
infix fun Iterable<LogItem>.shouldHaveWarningContaining(text: String) {
    if (!any { it isWarningContaining text }) {
        throw AssertionError(
            "LogList does not contain WARN containing $text\nLog lines:\n${joinToString(separator = "\n")}"
        )
    }
}

/**
 * Test whether a collection of [LogItem] has a `WARN` entry matching the specified [Regex].
 */
infix fun Iterable<LogItem>.shouldHaveWarningMatching(regex: Regex) {
    if (!any { it isWarningMatching regex }) {
        throw AssertionError("LogList does not contain WARN $regex\nLog lines:\n${joinToString(separator = "\n")}")
    }
}

/**
 * Test whether a collection of [LogItem] has an `ERROR` entry with the specified text.
 */
infix fun Iterable<LogItem>.shouldHaveError(text: String) {
    if (!any { it isError text }) {
        throw AssertionError("LogList does not contain ERROR $text\nLog lines:\n${joinToString(separator = "\n")}")
    }
}

/**
 * Test whether a collection of [LogItem] has an `ERROR` entry containing the specified text.
 */
infix fun Iterable<LogItem>.shouldHaveErrorContaining(text: String) {
    if (!any { it isErrorContaining text }) {
        throw AssertionError(
            "LogList does not contain ERROR containing $text\nLog lines:\n${joinToString(separator = "\n")}"
        )
    }
}

/**
 * Test whether a collection of [LogItem] has an `ERROR` entry matching the specified [Regex].
 */
infix fun Iterable<LogItem>.shouldHaveErrorMatching(regex: Regex) {
    if (!any { it isErrorMatching regex }) {
        throw AssertionError("LogList does not contain ERROR $regex\nLog lines:\n${joinToString(separator = "\n")}")
    }
}
