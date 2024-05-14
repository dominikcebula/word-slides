[![CI Pipeline](https://github.com/dominikcebula/word-slides/actions/workflows/gradle.yml/badge.svg)](https://github.com/dominikcebula/word-slides/actions/workflows/gradle.yml)

# Into

This repository contains simple "word-slides" algorithm implementation in Java 21 with Tests in Spock / Groovy.

Goal of this project is to solve the problem in a functional way was to show example usages of vavr
library (http://www.vavr.io/) that implements elements of
functional programming in Java.

# Personal note

Usage of functional programming paradigm in some cases creates code harder to understand when compared to imperative
programming. This project solves described problem in a functional way, however personally for me it would be easier to
read imperative approach.

# Tools used

* Java 21
* Spring Boot
* Lombok
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

# Algorithm description

## Overview

Algorithm accepts as an input "sentence" and returns a map of "slides" present in a
"store", associated to a number returned from a "store" for a given words "slide".

## Example

Given input sentence: "Mary went Mary's gone",

and given store that contains following slides associated with random values:

* "Mary": "some random int",
* "Mary gone": "some random int",
* "Mary's gone": "some random int",
* "went Mary's": "some random int",
* "went": "some random int",

Algorithm should provide following output:

* "went Mary's": "some random int",
* "Mary": "some random int"

## Description

Algorithm should analyze given input "sentence", and should search for the longest "slides" that exists in a store.
For each "slide" found in a "store" algorithm should return output map that shows "slide" and number value association.

Finding some "slide" in store means that it is "consumed" from the
input sentence, and subsequent slides, which were part of consumed slide are no longer valid
candidates, e.g. if "went Mary's" slide was found in store, then "went" is no longer valid
candidate (unless the sentence was for example "Mary went Mary's went").

Longer slides are "preferred" - it means "went Mary's" should consume these words before "went" has a chance
to do the same.

## Definitions

* word - one or more consecutive characters excluding whitespace; words
  are separated by whitespace in a sentence. For example "Mary went Mary's gone"
  has 4 words ("Mary", "went", "Mary's", "gone")

* sentence - collection of subsequent "words" used as input data that contains "slides". For example "Mary went Mary's
  gone"

* slide - any combination of consecutive "words" separated by single space, e.g. "Mary
  went Mary's gone" has a following slides:
    * one 4-word - "Mary went Mary's gone"
    * two 3-word - "Mary went Mary's", "went Mary's gone"
    * three 2-word - "Mary went", "went Mary's", "Mary's gone"
    * four 1-word - "Mary", "went", "Mary’s", "gone"

* store - in-memory structure that stores "slides" associated with a given number. Supports two operations:
    * check if given "slide" exists in the store
        * in this version of algorithm this operation should be based on random values, should return randomly true or
          false
    * get a value for a given "slide"
        * in this version of algorithm this operation should be based on random values, should return randomly value
          between 1 and 10

## Requirements

* Algorithm should not be coupled with Input Data Reader
    * implementation should use program arguments as input, but it should be possible to easily switch implementation
      with other ways of data input
* Algorithm should not be coupled with Output Data Write
    * implementation should use standard output, but it should be possible to easily switch implementation
      with other ways of data output
* Algorithm should not be coupled with Store implementation
    * implementation should use random store, but it should be possible to easily switch implementation
      with different store implementation

## Input

As sentence, for example: "Mary went Mary's gone"

## Output

Map that contains "slides" associated to random numbers from the "store"

* "Mary": "some random int",
* "Mary gone": "some random int",
* "Mary's gone": "some random int",
* "went Mary's": "some random int",
* "went": "some random int",

# Author

Dominik Cebula

* https://dominikcebula.com/
* https://blog.dominikcebula.com/
* https://www.udemy.com/user/dominik-cebula/
