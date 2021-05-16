# log-front-kotlin

Logging interface in Kotlin.

## Usage

Provides a simple means of instantiating a `Logger`, regardless of whether the call is made from a class or its
companion object.

```kotlin
    var log by LoggerDelegate()
```

## Dependency Specification

The latest version of the library is 1.0, and it may be obtained from the Maven Central repository.

### Maven
```xml
    <dependency>
      <groupId>net.pwall.log</groupId>
      <artifactId>log-front-kotlin</artifactId>
      <version>1.0</version>
    </dependency>
```
### Gradle
```groovy
    implementation 'net.pwall.log:log-front-kotlin:1.0'
```
### Gradle (kts)
```kotlin
    implementation("net.pwall.log:log-front-kotlin:1.0")
```

Peter Wall

2021-05-16
