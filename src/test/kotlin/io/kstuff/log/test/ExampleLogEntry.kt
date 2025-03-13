package io.kstuff.log.test

import io.kstuff.log.Level

data class ExampleLogEntry(
    val time: Long,
    val name: String,
    val level: Level,
    val message: Any?,
    val throwable: Throwable?,
)
