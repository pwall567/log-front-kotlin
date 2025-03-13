/*
 * @(#) Loggers.kt
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

import java.time.Clock

import io.jstuff.log.Log

/**
 * The dynamic [LoggerFactory], which tries to create a [Logger] as follows:
 *
 * 1. If `slf4j` is available on the class path, create an [Slf4jLogger]
 * 2. If the system property `java.util.logging.config.file` is present, or if the file `logging.properties` is present
 * as a resource, create a [JavaLogger]
 * 3. Otherwise, create a [FormattingLogger]
 */
typealias DynamicLoggerFactory = io.jstuff.log.DynamicLoggerFactory

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
