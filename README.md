# log-front-kotlin

Logging interface in Kotlin.

## Usage

Provides a simple means of instantiating a `Logger`, regardless of whether the call is made from a class or its
companion object.

```kotlin
    var log by LoggerDelegate()
```

## Dependency Specification

The latest version of the library is 0.1, and it may be obtained from the Maven Central repository.

### Maven
```xml
    <dependency>
      <groupId>net.pwall.log</groupId>
      <artifactId>log-front-kotlin</artifactId>
      <version>0.1</version>
    </dependency>
```
### Gradle
```groovy
    implementation 'net.pwall.log:log-front-kotlin:0.1'
```
### Gradle (kts)
```kotlin
    implementation("net.pwall.log:log-front-kotlin:0.1")
```

Peter Wall

2020-10-16
