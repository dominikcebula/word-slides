package com.dominikcebula.word.slides.ds

import spock.lang.Specification

class IndexedSentenceTest extends Specification {
    def "should create empty sentence"() {
        when:
        def sentence = IndexedSentence.createEmptySentence()

        then:
        sentence.length() == 0
        sentence.asString() == ""
    }

    def "should create empty sentence on double space"() {
        when:
        def sentence = IndexedSentence.createIndexedSentence("  ")

        then:
        sentence.length() == 0
        sentence.asString() == ""
    }

    def "should create indexed sentence"() {
        when:
        def sentence = IndexedSentence.createIndexedSentence("one two  three")

        then:
        sentence.length() == 3
        sentence.asString() == "one two three"
        sentence.toString() == sentence.asString()
        sentence.indexedWords.asJava() == [ONE, TWO, THREE]
    }

    def "should create sub sentence"() {
        when:
        def subSentence = IndexedSentence.createIndexedSentence("one two  three    four")
                .subSentence(new Range(1, 3))

        then:
        subSentence.length() == 2
        subSentence.asString() == "two three"
        subSentence.toString() == subSentence.asString()
        subSentence.indexedWords.asJava() == [TWO, THREE]
    }

    public static final IndexedWord ONE = new IndexedWord("one", 0)
    public static final IndexedWord TWO = new IndexedWord("two", 1)
    public static final IndexedWord THREE = new IndexedWord("three", 2)
}
