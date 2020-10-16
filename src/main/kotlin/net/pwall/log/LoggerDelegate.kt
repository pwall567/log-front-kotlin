/*
 * @(#) LoggerDelegate.kt
 *
 * log-front-kotlin  Logging interface in Kotlin
 * Copyright (c) 2020 Peter Wall
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

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty
import kotlin.reflect.full.companionObject

/**
 * Get a [Logger] for the enclosing class, even when invoked from a `companion object`.
 *
 * @author  Peter Wall
 */
class LoggerDelegate<R: Any>(private val loggerFactory: LoggerFactory = LoggerFactory.getDefault()) :
        ReadOnlyProperty<R, Logger> {

    private var logger: Logger = delegateLogger

    override fun getValue(thisRef: R, property: KProperty<*>): Logger {
        if (logger === delegateLogger) {
            delegateLogger.trace { "Getting logger for ${thisRef::class}" }
            logger = loggerFactory.getLogger(thisRef.javaClass.getCompanionParent().canonicalName)
        }
        return logger
    }

    private fun Class<*>.getCompanionParent(): Class<*> = when (this) {
        enclosingClass?.kotlin?.companionObject?.java -> enclosingClass
        else -> this
    }

    companion object {
        val delegateLogger: Logger = LoggerFactory.getDefaultLogger(LoggerDelegate::class.qualifiedName)
    }

}
