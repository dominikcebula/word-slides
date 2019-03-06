package com.dominikcebula.word.slides.finder

import com.dominikcebula.word.slides.ds.IndexedSentence
import com.dominikcebula.word.slides.ds.IndexedWord
import spock.lang.Specification

import static com.dominikcebula.word.slides.ds.IndexedSentenceForTestingFactory.createIndexedSentence

class CombinationGeneratorTest extends Specification {

    CombinationGenerator combinationGenerator = new CombinationGenerator()

    def "should not generate any combinations for empty sentence"() {
        when:
        def combinations = combinationGenerator.generate(IndexedSentence.createIndexedSentence(""))

        then:
        combinations.isEmpty()
    }

    def "should generate one combination for one word sentence"() {
        when:
        def combinations = combinationGenerator.generate(IndexedSentence.createIndexedSentence("one"))

        then:
        combinations.size() == 1
        combinations.asList() == [createIndexedSentence([ONE])]
    }

    def "should generate three combinations for two word sentence"() {
        when:
        def combinations = combinationGenerator.generate(IndexedSentence.createIndexedSentence("one two"))

        then:
        combinations.size() == 3
        combinations.asList() == [
                createIndexedSentence([ONE]),
                createIndexedSentence([ONE, TWO]),
                createIndexedSentence([TWO])
        ]
    }

    def "should generate ten combinations for four word sentence"() {
        when:
        def combinations = combinationGenerator.generate(IndexedSentence.createIndexedSentence("one two three four"))

        then:
        combinations.size() == 10
        combinations.asList() == [
                createIndexedSentence([ONE]),
                createIndexedSentence([ONE, TWO]),
                createIndexedSentence([ONE, TWO, THREE]),
                createIndexedSentence([ONE, TWO, THREE, FOUR]),
                createIndexedSentence([TWO]),
                createIndexedSentence([TWO, THREE]),
                createIndexedSentence([TWO, THREE, FOUR]),
                createIndexedSentence([THREE]),
                createIndexedSentence([THREE, FOUR]),
                createIndexedSentence([FOUR]),
        ]
    }

    private static final IndexedWord ONE = new IndexedWord("one", 0)
    private static final IndexedWord TWO = new IndexedWord("two", 1)
    private static final IndexedWord THREE = new IndexedWord("three", 2)
    private static final IndexedWord FOUR = new IndexedWord("four", 3)
}
