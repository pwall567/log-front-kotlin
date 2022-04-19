/*
 * @(#) LoggerUtil.kt
 *
 * log-front-kotlin  Logging interface in Kotlin
 * Copyright (c) 2020, 2021, 2022 Peter Wall
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
 * Get a [Logger] for a Kotlin [KClass], using the optional [Level] and [Clock]. (Kotlin equivalent of same function
 * taking a Java [Class]).
 *
 * @receiver            [LoggerFactory]
 * @param   kotlinClass a Kotlin [KClass]
 * @param   level       the [Level]
 * @param   clock       the [Clock]
 * @param   <L>         the [Logger] type
 * @return              a [Logger]
 */
fun <L: Logger> LoggerFactory<L>.getLogger(
    kotlinClass: KClass<*>,
    level: Level = defaultLevel,
    clock: Clock = defaultClock,
): L = getLogger(kotlinClass.qualifiedName, level, clock)

/**
 * Output an error message supplied by a lambda, along with a [Throwable].  This duplicates a function in the [Logger]
 * class, but with the parameters reversed to allow the use of Kotlin lambda syntax.
 *
 * @param   throwable           the [Throwable]
 * @param   messageSupplier     the message supplier lambda
 */
fun Logger.error(throwable: Throwable, messageSupplier: () -> Any) = error(messageSupplier, throwable)

/**
 * Test whether a [LogItem] represents a message with level `TRACE` and the specified text.
 *
 * @receiver        a [LogItem]
 * @param   text    the text to be compared
 * @return          `true` if the [LogItem] matches
 */
infix fun LogItem.isTrace(text: String): Boolean = level == Level.TRACE && this.text == text

/**
 * Test whether a [LogItem] represents a message with level `TRACE` and the specified text.
 *
 * @receiver        a [LogItem]
 * @param   regex   the [Regex]
 * @return          `true` if the [LogItem] matches
 */
infix fun LogItem.isTrace(regex: Regex): Boolean = level == Level.TRACE && regex.containsMatchIn(text)

/**
 * Test whether a [LogItem] represents a message with level `DEBUG` and the specified text.
 *
 * @receiver        a [LogItem]
 * @param   text    the text to be compared
 * @return          `true` if the [LogItem] matches
 */
infix fun LogItem.isDebug(text: String): Boolean = level == Level.DEBUG && this.text == text

/**
 * Test whether a [LogItem] represents a message with level `DEBUG` and the specified text.
 *
 * @receiver        a [LogItem]
 * @param   regex   the [Regex]
 * @return          `true` if the [LogItem] matches
 */
infix fun LogItem.isDebug(regex: Regex): Boolean = level == Level.DEBUG && regex.containsMatchIn(text)

/**
 * Test whether a [LogItem] represents a message with level `INFO` and the specified text.
 *
 * @receiver        a [LogItem]
 * @param   text    the text to be compared
 * @return          `true` if the [LogItem] matches
 */
infix fun LogItem.isInfo(text: String): Boolean = level == Level.INFO && this.text == text

/**
 * Test whether a [LogItem] represents a message with level `INFO` and text that matches the given [Regex].
 *
 * @receiver        a [LogItem]
 * @param   regex   the [Regex]
 * @return          `true` if the [LogItem] matches
 */
infix fun LogItem.isInfo(regex: Regex): Boolean = level == Level.INFO && regex.containsMatchIn(text)

/**
 * Test whether a [LogItem] represents a message with level `WARN` and the specified text.
 *
 * @receiver        a [LogItem]
 * @param   text    the text to be compared
 * @return          `true` if the [LogItem] matches
 */
infix fun LogItem.isWarning(text: String): Boolean = level == Level.WARN && this.text == text

/**
 * Test whether a [LogItem] represents a message with level `WARN` and text that matches the given [Regex].
 *
 * @receiver        a [LogItem]
 * @param   regex   the [Regex]
 * @return          `true` if the [LogItem] matches
 */
infix fun LogItem.isWarning(regex: Regex): Boolean = level == Level.WARN && regex.containsMatchIn(text)

/**
 * Test whether a [LogItem] represents a message with level `ERROR` and the specified text.
 *
 * @receiver        a [LogItem]
 * @param   text    the text to be compared
 * @return          `true` if the [LogItem] matches
 */
infix fun LogItem.isError(text: String): Boolean = level == Level.ERROR && this.text == text

/**
 * Test whether a [LogItem] represents a message with level `ERROR` and text that matches the given [Regex].
 *
 * @receiver        a [LogItem]
 * @param   regex   the [Regex]
 * @return          `true` if the [LogItem] matches
 */
infix fun LogItem.isError(regex: Regex): Boolean = level == Level.ERROR && regex.containsMatchIn(text)
