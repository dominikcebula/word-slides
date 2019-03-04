package com.dominikcebula.word.slides.finder

import com.dominikcebula.word.slides.store.StaticStoreForTesting
import com.dominikcebula.word.slides.store.Store
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import spock.lang.Specification

@SpringBootTest
class SlidesFinderIntegrationTest extends Specification {

    @Autowired
    SlidesFinder slidesFinder
    @Autowired
    StaticStoreForTesting staticStoreForTesting

    def "should solve correctly example from task description"() {
        given:
        staticStoreForTesting.setSlidesValues([
                "Mary"       : 1,
                "Mary gone"  : 2,
                "Mary's gone": 3,
                "went Mary's": 4,
                "went"       : 5
        ])

        when:
        def slides = slidesFinder.findSlides("Mary went Mary's gone")

        then:
        slides == ["went Mary's": 4, "Mary": 1]
    }

    def "should find nothing in empty sentence"() {
        when:
        def slides = slidesFinder.findSlides("")

        then:
        slides.isEmpty()
    }

    def "should find value for one word"() {
        given:
        staticStoreForTesting.setSlidesValues(["one": 1])

        when:
        def slides = slidesFinder.findSlides("one")

        then:
        slides == ["one": 1]
    }

    def "should not find any value when value is missing from store"() {
        given:
        staticStoreForTesting.setSlidesValues(["two": 1])

        when:
        def slides = slidesFinder.findSlides("one")

        then:
        slides.isEmpty()
    }

    def "should find values for three words"() {
        given:
        staticStoreForTesting.setSlidesValues(["one": 1, "two": 2, "three": 3])

        when:
        def slides = slidesFinder.findSlides("one two three four")

        then:
        slides == ["one": 1, "two": 2, "three": 3]
    }

    def "should find longest slide"() {
        given:
        staticStoreForTesting.setSlidesValues(["one two three": 1, "one": 2, "two": 3, "three": 4, "four": 5])

        when:
        def slides = slidesFinder.findSlides("one two three four")

        then:
        slides == ["one two three": 1, "four": 5]
    }

    def "should avoid consumption conflict"() {
        given:
        staticStoreForTesting.setSlidesValues(["one two three": 1, "two three four": 2, "one two": 3, "three four": 4, "four": 5])

        when:
        def slides = slidesFinder.findSlides("one two three four")

        then:
        slides == ["one two three": 1, "four": 5]
    }

    def "should pick longest sentence and consume all other words"() {
        given:
        staticStoreForTesting.setSlidesValues(["one two three" : 1, "two three four": 2,
                                               "one two"       : 3, "three four": 4, "four": 5,
                                               "five six seven": 6, "one two three four five six seven": 0])

        when:
        def slides = slidesFinder.findSlides("one two three four five six seven")

        then:
        slides == ["one two three four five six seven": 0]
    }

    def "should avoid multiple words conflicts"() {
        given:
        staticStoreForTesting.setSlidesValues(["one two three": 1, "two three four": 2, "three four five": 3,
                                               "four five six": 4, "five six seven": 5])

        when:
        def slides = slidesFinder.findSlides("one two three four five six seven")

        then:
        slides == ["one two three": 1, "four five six": 4]
    }
}
