# log-front-kotlin

[![Build Status](https://github.com/pwall567/log-front-kotlin/actions/workflows/build.yml/badge.svg)](https://github.com/pwall567/log-front-kotlin/actions/workflows/build.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Kotlin](https://img.shields.io/static/v1?label=Kotlin&message=v2.0.21&color=7f52ff&logo=kotlin&logoColor=7f52ff)](https://github.com/JetBrains/kotlin/releases/tag/v2.0.21)
[![Maven Central](https://img.shields.io/maven-central/v/io.kstuff/log-front-kotlin?label=Maven%20Central)](https://central.sonatype.com/artifact/io.jstuff/log-front-kotlin)

Logging interface in Kotlin.

This library provides access to the [`log-front`](https://github.com/pwall567/log-front) Java library in a Kotlin
idiomatic manner.

## Background

This is a Kotlin implementation of the [`log-front-api`](https://github.com/pwall567/log-front-api) (see that library
for a description of the motivation behind its creation).

The default behaviour is to use reflection to find the `slf4j` classes in the classpath &ndash; if they are found all
logging calls will be redirected to that mechanism.
The second choice is to use the Java Logging framework if indicated by the presence of a system property or
configuration associated with that framework;
otherwise log messages will be output using a logger that writes to a `java.io.PrintStream` (default `stdout`).

If it is necessary to fit in with an existing logging framework other than `slf4j` or Java Logging, the API is designed
to be simple to implement so that a thin interfacing layer can be created, accepting this API and invoking the required
target logging mechanism.

New from version 4.0 onward is multi-line logging.
This is a security measure &ndash; if an attacker is aware that input text is included in a log message, they may try to
create fake log events by including a newline followed by what looks like a log message prefix in their text.
The library now splits messages containing line terminators into separate invocations of the underlying log output
function, so each line of the log has the correct prefix.

(The name `log-front` is a play on the name of the popular [Logback](https://logback.qos.ch/) project;
also on the fact that it is a front-end &ndash; a fa&ccedil;ade &ndash; to an underlying logging system.)

## Usage

### Quick Start

To instantiate a `Logger` for the current class, regardless of whether the call is made from within the class or its
companion object:
```kotlin
    val log = getLogger()   // requires: import io.kstuff.log.getLogger
```

To output a log "INFO"-level message:
```kotlin
        log.info { "message content" }
```
Because the lambda is evaluated only if logging is enabled for this logger and this level, complex substitutions may be
used in the message with no loss of efficiency.

There are similar functions `trace`, `debug`, `warn` and `error`, and an additional version of `error` that takes a
`Throwable` as the first parameter:
```kotlin
        log.error(exception) { "Something went wrong!" }
```

### `Level`

A fundamental concept in logging is the `Level`.
This library uses five logging levels:

- `TRACE`
- `DEBUG`
- `INFO`
- `WARN`
- `ERROR`

For information on how these levels map to the levels used by the underlying logging system, see the documentation
relating to the implementation classes.

### `getLogger()`

There are several top-level functions to acquire a `Logger` instance, but in practice, most users will just use the
first (and simplest) one:

- `getLogger()`
- `getLogger(name: String)`
- `getLogger(javaClass: Class<*>)`
- `getLogger(kClass: KClass<*>)`
- `getLogger(level: Level)`
- `getLogger(name: String, level: Level)`
- `getLogger(javaClass: Class<*>, level: Level)`
- `getLogger(kClass: KClass<*>, level: Level)`
- `getLogger(clock: Clock)`
- `getLogger(name: String, clock: Clock)`
- `getLogger(javaClass: Class<*>, clock: Clock)`
- `getLogger(kClass: KClass<*>, clock: Clock)`
- `getLogger(level: Level, clock: Clock)`
- `getLogger(name: String, level: Level, clock: Clock)`
- `getLogger(javaClass: Class<*>, level: Level, clock: Clock)`
- `getLogger(kClass: KClass<*>, level: Level, clock: Clock)`

When no logger name is provided, the name will be determined automatically from the calling class.
Otherwise, the name may be specified as a `String`, or as a Java `Class` or a Kotlin `KClass`, in which case the
fully-qualified class name will be used.
The name must contain only ASCII characters (`0x00..0x7E`).

The `level` and `clock` (`java.time.Clock`), if specified, will be provided to the `LoggerFactory` to be used in the
instantiation of the `Logger`, although not all implementations will make use of these parameters.

### `Logger`

The `Logger` has three properties:

| Name    | Type     | Mutable |
|---------|----------|---------|
| `name`  | `String` | No      |
| `level` | `Level`  | Yes     |
| `clock` | `Clock`  | Yes     |

In many cases, the underlying implementation will ignore changes to the `level` or `clock`, so changing these properties
should usually be limited to test functions.

The functions to output a log message should be familiar to most:

- `trace(message)`
- `trace { messageFunction() }`
- `debug(message)`
- `debug { messageFunction() }`
- `info(message)`
- `info { messageFunction() }`
- `warn(message)`
- `warn { messageFunction() }`
- `error(message)`
- `error { messageFunction() }`

The `Logger` will check whether the level implied by the name is enabled for that instance, and will output the log
message only if it is.
Importantly, where the form of the function which takes a lambda is used, the lambda will not be executed if the
`Logger` is not enabled for that level.

There are also functions that take a variable `Level`:

- `log(level, message)`
- `log(level) { messageFunction() }`

And there are versions of the `error` function that take a `Throwable` (usually an `Exception`) to indicate the cause of
the error:

- `error(cause, message)`
- `error(cause) { messageFunction() }`

It is important to note that the `message` (or the result of the `messageFunction()`) is of type `Any?`; the text that
will appear in the log will be the `toString()` of the object (the `toString()` of a `String` is itself).
This allows for an alternative form of lazy message string creation, since the `toString()` is invoked only after the
level has been checked.
It also means that a `LogListener` has access to parts of the object that do not appear in the text.

## Debugging

### `LogListener`

The library provides a built-in mechanism for testing whether log items are output as expected.
If an object extending the `LogListener` abstract base class is created, it will be added to a list of listeners and
called for every log event (calling `close()` on the listener will remove it from the list).
A simple implementation `LogList` is provided &ndash; this stores the log items in a list for later examination.

The `LogListener` class implements the `AutoCloseable` interface, allowing it to be used in a `use { }` block.
This ensures that the listener is removed from the list when it is no longer required.

The mechanism is intended to be completely non-intrusive &ndash; it should be possible to write code that outputs log
messages using `Logger` objects obtained from the default `LoggerFactory`, and then to create tests that intercept the
log messages and check for correctness.
When the code is run in a production situation with no listeners, the overhead of checking for them will be minuscule.

**Note that the listener mechanism is global (and simplistic) &ndash; it is intended solely for unit testing purposes.**
Only log messages output via this library will be presented to the listener; messages output to the underlying logging
system by other means will not be visible.
And because logging systems create their own timestamps, the times on messages captured by this mechanism may differ
very slightly from the times generated by the underlying system.

### `LoggerDelegate`

The `LoggerDelegate` class from previous versions of the library has been removed; its use has been superseded by the
`getLogger()` functions.

## Combining with Java `log-front`

This library is based on the Java [`log-front`](https://github.com/pwall567/log-front) library, and in many cases, the
classes of this library are declared as a `typealias` onto the equivalent class in the Java library.
This means that named parameters are not available for function calls to these classes, but otherwise, there should be
little noticeable effect from this arrangement.

Importantly, in a mixed-language project, a `Logger` declared in one language may be used in the other (using the Java
class name) and tests that use `LogListener` and `LogItem` will include log events generated in both languages.

## Dependency Specification

The latest version of the library is 6.2, and it may be obtained from the Maven Central repository.

### Maven
```xml
    <dependency>
      <groupId>io.kstuff</groupId>
      <artifactId>log-front-kotlin</artifactId>
      <version>6.2</version>
    </dependency>
```
### Gradle
```groovy
    implementation 'io.kstuff:log-front-kotlin:6.2'
```
### Gradle (kts)
```kotlin
    implementation("io.kstuff:log-front-kotlin:6.2")
```

Peter Wall

2025-03-14
