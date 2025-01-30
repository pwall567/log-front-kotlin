/*
 * @(#) FormattingLoggerTest.kt
 *
 * log-front-kotlin  Logging interface in Kotlin
 * Copyright (c) 2022 Peter Wall
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

import io.jstuff.log.FormattingLoggerFactory
import io.jstuff.log.Log

import io.kstuff.log.getLogger

class FormattingLoggerTest {

    @Test fun `should use FormattingLogger`() {
        val loggerFactory = FormattingLoggerFactory.getBasic()
        val log = loggerFactory.logger
        log.info { "Hello!" }
        log.formatter.isColouredLevel = false
        log.info { "Monochrome" }
    }

    @Test fun `should set FormattingLogger as default`() {
        Log.setDefaultLoggerFactory(FormattingLoggerFactory.getBasic())
        val log = getLogger()
        log.info { "Bonjour!" }
    }

}
