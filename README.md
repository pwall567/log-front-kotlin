# log-front-kotlin

[![Build Status](https://travis-ci.com/pwall567/log-front-kotlin.svg?branch=main)](https://app.travis-ci.com/github/pwall567/log-front-kotlin)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Kotlin](https://img.shields.io/static/v1?label=Kotlin&message=v1.4.0&color=blue&logo=kotlin)](https://github.com/JetBrains/kotlin/releases/tag/v1.4.0)
[![Maven Central](https://img.shields.io/maven-central/v/net.pwall.log/log-front-kotlin?label=Maven%20Central)](https://search.maven.org/search?q=g:%22net.pwall.log%22%20AND%20a:%22log-front-kotlin%22)

Logging interface in Kotlin.

This library provides access to the [`log-front`](https://github.com/pwall567/log-front) Java library in a Kotlin
idiomatic manner.

## Usage

### Logging

The functionality of this library is mostly documented along with the Java library.
There are a small number of additional functions, along with some different patterns of usage.

There is an additional `getLogger` function on `LoggerFactory` which takes a Kotlin `KClass`:
```kotlin
    val log = LoggerFactory.getDefault().getLogger(ThisClass::class)
```

To instantiate a `Logger` for the current class, regardless of whether the call is made from within the class or its
companion object:
```kotlin
    var log by LoggerDelegate()
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

## Dependency Specification

The latest version of the library is 2.5, and it may be obtained from the Maven Central repository.

### Maven
```xml
    <dependency>
      <groupId>net.pwall.log</groupId>
      <artifactId>log-front-kotlin</artifactId>
      <version>2.5</version>
    </dependency>
```
### Gradle
```groovy
    implementation 'net.pwall.log:log-front-kotlin:2.5'
```
### Gradle (kts)
```kotlin
    implementation("net.pwall.log:log-front-kotlin:2.5")
```

Peter Wall

2022-01-04
