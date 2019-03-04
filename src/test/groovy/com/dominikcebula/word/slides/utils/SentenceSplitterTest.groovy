package com.dominikcebula.word.slides.utils

import spock.lang.Specification

class SentenceSplitterTest extends Specification {

    def "should create empty words for empty sentence"() {
        when:
        def words = SentenceSplitter.splitSentence("")

        then:
        words.length == 0
    }

    def "should create empty words for only spaces sentence"() {
        when:
        def words = SentenceSplitter.splitSentence("   ")

        then:
        words.length == 0
    }

    def "should create one word for one word sentence"() {
        when:
        def words = SentenceSplitter.splitSentence("one")

        then:
        words == ["one"].toArray()
    }

    def "should create three words for three word sentence"() {
        when:
        def words = SentenceSplitter.splitSentence("one  two    three")

        then:
        words == ["one", "two", "three"].toArray()
    }
}
