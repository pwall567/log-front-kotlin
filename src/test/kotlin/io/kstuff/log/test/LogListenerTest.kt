package io.kstuff.log.test

import kotlin.test.Test

import io.kstuff.test.shouldBe
import io.kstuff.test.shouldBeSameInstance

import io.kstuff.log.Level
import io.kstuff.log.getLogger

class LogListenerTest {

    @Test fun `should store log items in list`() {
        ExampleLogListener().use { list ->
            val log = getLogger("xxx")
            log.info("message 1")
            log.warn("message 2")
            val exception = IllegalStateException("Another dummy")
            log.error(exception, "message 3")
            val items = list.iterator()
            with(items.next()) {
                name shouldBe "xxx"
                level shouldBe Level.INFO
                message shouldBe "message 1"
                throwable shouldBe null
            }
            with(items.next()) {
                name shouldBe "xxx"
                level shouldBe Level.WARN
                message shouldBe "message 2"
                throwable shouldBe null
            }
            with(items.next()) {
                name shouldBe "xxx"
                level shouldBe Level.ERROR
                message shouldBe "message 3"
                throwable shouldBeSameInstance exception
            }
        }
    }

}
