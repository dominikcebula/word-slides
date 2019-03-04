package com.dominikcebula.word.slides.finder;

import com.dominikcebula.word.slides.ds.CumulativeConsumedWords;
import com.dominikcebula.word.slides.ds.IndexedSentence;
import com.dominikcebula.word.slides.ds.IndexedWord;
import io.vavr.collection.List;
import io.vavr.collection.Set;
import org.springframework.stereotype.Component;

import static io.vavr.API.*;

@Component
class CumulativeConsumedWordsAppender {

    CumulativeConsumedWords append(CumulativeConsumedWords cumulativeConsumedWords, IndexedSentence indexedSentence) {
        var cumulativeConsumedWordsBeforeSentence = cumulativeConsumedWords.getCumulativeWords();
        var wordsAtCurrentSentence = indexedSentence.getIndexedWords();

        boolean wordConsumptionConflict = containsAny(cumulativeConsumedWordsBeforeSentence, wordsAtCurrentSentence);

        var cumulativeConsumedWordsAtSentence = getCumulativeConsumedWordsAtSentence(cumulativeConsumedWordsBeforeSentence, wordsAtCurrentSentence, wordConsumptionConflict);

        return new CumulativeConsumedWords(indexedSentence, cumulativeConsumedWordsAtSentence, wordConsumptionConflict);
    }

    private boolean containsAny(Set<IndexedWord> wordsConsumedBeforeCurrentSentence, List<IndexedWord> wordsFromCurrentSentence) {
        return wordsFromCurrentSentence
                .map(wordsConsumedBeforeCurrentSentence::contains)
                .reduce((a, b) -> a || b);
    }

    private Set<IndexedWord> getCumulativeConsumedWordsAtSentence(Set<IndexedWord> cumulativeConsumedWordsBeforeSentence, List<IndexedWord> wordsAtCurrentSentence, boolean wordConsumptionConflict) {
        return Match(wordConsumptionConflict).of(
                Case($(true), cumulativeConsumedWordsBeforeSentence),
                Case($(false), cumulativeConsumedWordsBeforeSentence.addAll(wordsAtCurrentSentence))
        );
    }
}
