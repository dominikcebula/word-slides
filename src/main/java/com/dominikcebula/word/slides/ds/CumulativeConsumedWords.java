package com.dominikcebula.word.slides.ds;

import io.vavr.collection.HashSet;
import io.vavr.collection.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CumulativeConsumedWords {
    private IndexedSentence parentSentence;
    private Set<IndexedWord> cumulativeWords;
    private boolean wordConsumptionConflict;

    public CumulativeConsumedWords() {
        parentSentence = IndexedSentence.createEmptySentence();
        cumulativeWords = HashSet.empty();
        wordConsumptionConflict = false;
    }

    public boolean sentenceNotEmpty() {
        return parentSentence.length() > 0;
    }

    public boolean noConsumptionConflict() {
        return !wordConsumptionConflict;
    }
}
