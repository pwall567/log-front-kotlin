# log-front-kotlin

[![Build Status](https://travis-ci.com/pwall567/log-front-kotlin.svg?branch=main)](https://app.travis-ci.com/github/pwall567/log-front-kotlin)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Kotlin](https://img.shields.io/static/v1?label=Kotlin&message=v1.6.10&color=7f52ff&logo=kotlin&logoColor=7f52ff)](https://github.com/JetBrains/kotlin/releases/tag/v1.5.20)
[![Maven Central](https://img.shields.io/maven-central/v/net.pwall.log/log-front-kotlin?label=Maven%20Central)](https://search.maven.org/search?q=g:%22net.pwall.log%22%20AND%20a:%22log-front-kotlin%22)

Logging interface in Kotlin.

This library provides access to the [`log-front`](https://github.com/pwall567/log-front) Java library in a Kotlin
idiomatic manner.

## Usage

### Quick Start

To instantiate a `Logger` for the current class, regardless of whether the call is made from within the class or its
companion object:
```kotlin
    val log = getLogger()   // requires: import net.pwall.log.getLogger
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

### More Detail

The functionality of this library is largely documented with the Java library.
There are a small number of additional functions, along with some different patterns of usage.

There are several top-level functions to acquire a `Logger` instance.
These should be used in place of the functions of the `Log` class &ndash; they achieve the same result, but the return
type of these functions is non-nullable, as opposed to the Java functions which are of indeterminate nullability.

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

The `Level` and `Clock`, if specified, will be provided to the `LoggerFactory` to be used in the instantiation of the
`Logger`, although as noted in the description of the `log-front` library, not all implementations will make use of
these parameters.

There is also an additional `getLogger` function on `LoggerFactory` which takes a Kotlin `KClass`:
```kotlin
    val log = loggerFactory.getLogger(ThisClass::class)
```

The function also allows the `Level` and a `Clock` to be specified as optional parameters:
```kotlin
    val log = loggerFactory.getLogger(ThisClass::class, level = Level.INFO,
            clock = Clock.fixed(Instant.now(), ZoneOffset.UTC))
```

### Debugging

This library also includes some additional functions to help with the use of the
[`LogListener`](https://github.com/pwall567/log-front#loglistener) functionality of the Java library.

To test whether a `LogItem` is an `INFO`-level item with the text "Hello":
```kotlin
        if (logItem isInfo "Hello") {
            // whatever...
        }
```

Or to test whether a `LogItem` is an `ERROR`-level item with a message starting with "File not found ":
```kotlin
        if (logItem isError Regex("^File not found ")) {
            // whatever...
        }
```

There are, of course, `isTrace`, `isDebug` and `isWarning` functions in both the exact match (string) and regex forms.

### `LoggerDelegate`

The `LoggerDelegate` class from previous versions of the library is retained, but its use has been superseded by the
`getLogger()` functions.
It may be deprecated in future releases.

## Dependency Specification

The latest version of the library is 5.1.2, and it may be obtained from the Maven Central repository.

### Maven
```xml
    <dependency>
      <groupId>net.pwall.log</groupId>
      <artifactId>log-front-kotlin</artifactId>
      <version>5.1.2</version>
    </dependency>
```
### Gradle
```groovy
    implementation 'net.pwall.log:log-front-kotlin:5.1.2'
```
### Gradle (kts)
```kotlin
    implementation("net.pwall.log:log-front-kotlin:5.1.2")
```

Peter Wall

2022-10-19
