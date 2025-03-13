package io.kstuff.log.test

import io.kstuff.log.Level
import io.kstuff.log.Logger
import io.kstuff.log.LogListener

class ExampleLogListener : LogListener() {

    private val list = mutableListOf<ExampleLogEntry>()

    override fun receive(time: Long, logger: Logger, level: Level, message: Any?, throwable: Throwable?) {
        list.add(
            ExampleLogEntry(
                time = time,
                name = logger.name,
                level = level,
                message = message,
                throwable = throwable,
            )
        )
    }

    fun iterator(): Iterator<ExampleLogEntry> = list.iterator()

}
