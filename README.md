# Into

This repository contains simple "word-slides" algorithm implementation in Java 11 with Tests in Groovy.

Goal of this project was to show example usages of vavr library (http://www.vavr.io/) that implements elements of functional programming in Java.

# Tools used

* Java 21
* Spring Boot
* vavr
* Spock
* Groovy
* Gradle

# Compiling project

To compile and build project type:
```
gradle clean build
```

You can also build without running tests:
```
gradle clean build -x test
```

# Running Project

There are two ways to run project:
* Directly from Gradle
* Using executable JAR

## Running Project with Gradle

To run project from gradle, execute:
```
gradle bootRun -Pargs="This is an example sentence"
```

## Running Project with Executable JAR

To run project from Executable JAR, build project and then type:
```
java -jar build/libs/word-slides-1.0-SNAPSHOT.jar "This is an example"
```
