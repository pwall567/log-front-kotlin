/*
 * @(#) LoggerUtil.kt
 *
 * log-front-kotlin  Logging interface in Kotlin
 * Copyright (c) 2020, 2021, 2022, 2024 Peter Wall
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

import kotlin.reflect.KClass
import java.time.Clock

/**
 * Get a [Logger] from the default [LoggerFactory], using the name of the calling class and the default level and clock.
 */
fun getLogger(): Logger = Log.getLogger()

/**
 * Get a [Logger] from the default [LoggerFactory], using the name of the calling class, the specified level and the
 * default clock.
 */
fun getLogger(level: Level): Logger = Log.getLogger(level)

/**
 * Get a [Logger] from the default [LoggerFactory], using the name of the calling class, the default level and the
 * specified clock.
 */
fun getLogger(clock: Clock): Logger = Log.getLogger(clock)

/**
 * Get a [Logger] from the default [LoggerFactory], using the name of the calling class and the specified level and
 * clock.
 */
fun getLogger(level: Level, clock: Clock): Logger = Log.getLogger(level, clock)

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
 * Test whether a [LogItem] represents a message with level `TRACE` and the specified text.
 */
infix fun LogItem.isTrace(regex: Regex): Boolean = level == Level.TRACE && regex.containsMatchIn(message.toString())

/**
 * Test whether a [LogItem] represents a message with level `DEBUG` and the specified text.
 */
infix fun LogItem.isDebug(text: String): Boolean = level == Level.DEBUG && message == text

/**
 * Test whether a [LogItem] represents a message with level `DEBUG` and the specified text.
 */
infix fun LogItem.isDebug(regex: Regex): Boolean = level == Level.DEBUG && regex.containsMatchIn(message.toString())

/**
 * Test whether a [LogItem] represents a message with level `INFO` and the specified text.
 */
infix fun LogItem.isInfo(text: String): Boolean = level == Level.INFO && message == text

/**
 * Test whether a [LogItem] represents a message with level `INFO` and text that matches the given [Regex].
 */
infix fun LogItem.isInfo(regex: Regex): Boolean = level == Level.INFO && regex.containsMatchIn(message.toString())

/**
 * Test whether a [LogItem] represents a message with level `WARN` and the specified text.
 */
infix fun LogItem.isWarning(text: String): Boolean = level == Level.WARN && message == text

/**
 * Test whether a [LogItem] represents a message with level `WARN` and text that matches the given [Regex].
 */
infix fun LogItem.isWarning(regex: Regex): Boolean = level == Level.WARN && regex.containsMatchIn(message.toString())

/**
 * Test whether a [LogItem] represents a message with level `ERROR` and the specified text.
 */
infix fun LogItem.isError(text: String): Boolean = level == Level.ERROR && message == text

/**
 * Test whether a [LogItem] represents a message with level `ERROR` and text that matches the given [Regex].
 */
infix fun LogItem.isError(regex: Regex): Boolean = level == Level.ERROR && regex.containsMatchIn(message.toString())

/**
 * Filter list by name.
 */
fun Iterable<LogItem>.subList(name: String): Iterable<LogItem> = filter { it.name == name }

/**
 * Test whether a [LogList] has a `TRACE` entry with the specified text.
 */
infix fun Iterable<LogItem>.assertHasTrace(text: String) {
    if (!any {it isTrace text }) {
        throw AssertionError("LogList does not contain TRACE $text\nLog lines:\n${joinToString(separator = "\n")}")
    }
}

/**
 * Test whether a [LogList] has a `TRACE` entry matching the specified [Regex].
 */
infix fun Iterable<LogItem>.assertHasTrace(regex: Regex) {
    if (!any {it isTrace regex }) {
        throw AssertionError("LogList does not contain TRACE $regex\nLog lines:\n${joinToString(separator = "\n")}")
    }
}

/**
 * Test whether a [LogList] has a `DEBUG` entry with the specified text.
 */
infix fun Iterable<LogItem>.assertHasDebug(text: String) {
    if (!any {it isDebug text }) {
        throw AssertionError("LogList does not contain DEBUG $text\nLog lines:\n${joinToString(separator = "\n")}")
    }
}

/**
 * Test whether a [LogList] has a `DEBUG` entry matching the specified [Regex].
 */
infix fun Iterable<LogItem>.assertHasDebug(regex: Regex) {
    if (!any {it isDebug regex }) {
        throw AssertionError("LogList does not contain DEBUG $regex\nLog lines:\n${joinToString(separator = "\n")}")
    }
}

/**
 * Test whether a [LogList] has an `INFO` entry with the specified text.
 */
infix fun Iterable<LogItem>.assertHasInfo(text: String) {
    if (!any {it isInfo text }) {
        throw AssertionError("LogList does not contain INFO $text\nLog lines:\n${joinToString(separator = "\n")}")
    }
}

/**
 * Test whether a [LogList] has an `INFO` entry matching the specified [Regex].
 */
infix fun Iterable<LogItem>.assertHasInfo(regex: Regex) {
    if (!any {it isInfo regex }) {
        throw AssertionError("LogList does not contain INFO $regex\nLog lines:\n${joinToString(separator = "\n")}")
    }
}

/**
 * Test whether a [LogList] has a `WARN` entry with the specified text.
 */
infix fun Iterable<LogItem>.assertHasWarning(text: String) {
    if (!any {it isWarning text }) {
        throw AssertionError("LogList does not contain WARN $text\nLog lines:\n${joinToString(separator = "\n")}")
    }
}

/**
 * Test whether a [LogList] has a `WARN` entry matching the specified [Regex].
 */
infix fun Iterable<LogItem>.assertHasWarning(regex: Regex) {
    if (!any {it isWarning regex }) {
        throw AssertionError("LogList does not contain WARN $regex\nLog lines:\n${joinToString(separator = "\n")}")
    }
}

/**
 * Test whether a [LogList] has an `ERROR` entry with the specified text.
 */
infix fun Iterable<LogItem>.assertHasError(text: String) {
    if (!any {it isError text }) {
        throw AssertionError("LogList does not contain ERROR $text\nLog lines:\n${joinToString(separator = "\n")}")
    }
}

/**
 * Test whether a [LogList] has an `ERROR` entry matching the specified [Regex].
 */
infix fun Iterable<LogItem>.assertHasError(regex: Regex) {
    if (!any {it isError regex }) {
        throw AssertionError("LogList does not contain ERROR $regex\nLog lines:\n${joinToString(separator = "\n")}")
    }
}
