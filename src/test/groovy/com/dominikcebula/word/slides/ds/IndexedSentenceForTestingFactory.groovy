package com.dominikcebula.word.slides.ds

import static io.vavr.collection.List.ofAll

class IndexedSentenceForTestingFactory {
    static IndexedSentence createIndexedSentence(List<IndexedWord> indexedWords) {
        return new IndexedSentence(ofAll(indexedWords))
    }
}
