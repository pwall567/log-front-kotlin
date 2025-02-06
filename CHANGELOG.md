# Change Log

The format is based on [Keep a Changelog](http://keepachangelog.com/).

## [6.1] - 2025-02-06
### Changes
- `pom.xml`: updated version of `log-front`
- `LoggerUtil`: added `typealias` entries for classes in `log-front`

## [6.0] - 2025-01-30
### Changes
- `pom.xml`: moved to `io.kstuff` (package amd Maven group)
- `LoggerUtil`: added `isTraceContaining()`, `isDebugContaining()`, `isInfoContaining()`, `isWarningContaining()` and
  `isTraceContaining()`
- `LoggerUtil`: renamed `isXxxx()` taking `Regex` to `isXxxxMatching()`
- `LoggerUtil`: renamed `assertHasXxxx()` to `shouldHaveXxxx()`, renamed functions taking `Regex` as above
- `LoggerUtil`: added `shouldHaveXxxxContaining()` functions
### Removed
- `LoggerDelegate`: superseded by `getLogger()` functions

## [5.4] - 2025-01-26
### Changes
- `pom.xml`: upgraded Kotlin version to 2.0.21
- tests: converted to `should-test` library

## [5.3.1] - 2024-07-25
### Added
- `build.yml`, `deploy.yml`: converted project to GitHub Actions
### Changes
- `pom.xml`: upgraded Kotlin version to 1.9.24
### Removed
- `.travis.yml`

## [5.3] - 2023-02-25
### Changed
- `LoggerUtil.kt`: added `assertHasXxxx` functions
- `pom.xml`: updated to later version of `log-front`

## [5.2] - 2023-12-02
### Changed
- `pom.xml`: updated Kotlin version
- `pom.xml`: updated to later version of `log-front`

## [5.1.2] - 2022-10-19
### Changed
- `pom.xml`: updated to later version of `log-front`

## [5.1] - 2022-10-19
### Changed
- `pom.xml`: updated to later version of `log-front`

## [5.0] - 2022-06-22
### Changed
- `pom.xml`: updated to later version of `log-front`
- `LoggerUtil`: added `getLogger()` functions

## [4.0] - 2022-05-04
### Changed
- `pom.xml`: updated to later version of `log-front`

## [3.0] - 2022-04-20
### Changed
- `pom.xml`: updated to later version of `log-front`

## [2.6] - 2022-01-27
### Changed
- `pom.xml`: updated to later version of `log-front`

## [2.5] - 2022-01-04
### Changed
- `pom.xml`: updated to later version of `log-front`

## [2.4] - 2021-09-29
### Changed
- `pom.xml`: updated to later version of `log-front`

## [2.3] - 2021-08-29
### Changed
- `pom.xml`: updated to later versions of `log-front` and `maven-kotlin`

## [2.2] - 2021-06-26
### Changed
- `pom.xml`: updated to later version of `log-front`
- `LoggerUtil`: added `error` function with lambda as last parameter
- `travis.yml`: fixed error
- `README.md`: added documentation

## [2.1] - 2021-06-26
### Changed
- `pom.xml`: updated to later version of `log-front`

## [2.0] - 2021-06-15
### Changed
- `pom.xml`: updated to later version of `log-front`
- `LoggerUtil.kt`: added functions to check `LogItem` entries

## [1.0] - 2021-05-16
### Changed
- `pom.xml`: updated versions of `log-front` and `maven-kotlin`; bumped version to 1.0
### Added
- `.travis.yml`: new

## [0.1] - 2020-10-16
### Added
- all files: initial versions
