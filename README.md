# log-front-kotlin

[![Build Status](https://travis-ci.org/pwall567/log-front-kotlin.svg?branch=main)](https://travis-ci.org/pwall567/log-front-kotlin)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Kotlin](https://img.shields.io/static/v1?label=Kotlin&message=v1.4.0&color=blue&logo=kotlin)](https://github.com/JetBrains/kotlin/releases/tag/v1.4.0)
[![Maven Central](https://img.shields.io/maven-central/v/net.pwall.log/log-front-kotlin?label=Maven%20Central)](https://search.maven.org/search?q=g:%22net.pwall.log%22%20AND%20a:%22log-front-kotlin%22)

Logging interface in Kotlin.

This library provides access to the [`log-front`](https://github.com/pwall567/log-front) Java library in a Kotlin
idiomatic manner.
Apart from the instantiation function described below, all other functionality is documented along with the Java
library.

## Usage

Provides a simple means of instantiating a `Logger`, regardless of whether the call is made from a class or its
companion object.

```kotlin
    var log by LoggerDelegate()
```

## Dependency Specification

The latest version of the library is 2.0, and it may be obtained from the Maven Central repository.

### Maven
```xml
    <dependency>
      <groupId>net.pwall.log</groupId>
      <artifactId>log-front-kotlin</artifactId>
      <version>2.0</version>
    </dependency>
```
### Gradle
```groovy
    implementation 'net.pwall.log:log-front-kotlin:2.0'
```
### Gradle (kts)
```kotlin
    implementation("net.pwall.log:log-front-kotlin:2.0")
```

Peter Wall

2021-06-15
