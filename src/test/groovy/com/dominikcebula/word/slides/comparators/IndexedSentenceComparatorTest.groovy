package com.dominikcebula.word.slides.comparators

import spock.lang.Specification

import static com.dominikcebula.word.slides.ds.IndexedSentence.createIndexedSentence
import static io.vavr.collection.List.ofAll

class IndexedSentenceComparatorTest extends Specification {

    IndexedSentenceComparator indexedSentenceComparator = new IndexedSentenceComparator()

    def "should sort sentences from longest to shortest"() {
        given:
        def sentences = [
                createIndexedSentence("one"),
                createIndexedSentence("one two three"),
                createIndexedSentence("one two"),
                createIndexedSentence("one two three four")
        ]

        when:
        def sortedSentences = ofAll(sentences).sorted(indexedSentenceComparator)

        then:
        sortedSentences.asJava() == [
                createIndexedSentence("one two three four"),
                createIndexedSentence("one two three"),
                createIndexedSentence("one two"),
                createIndexedSentence("one")
        ]
    }
}
