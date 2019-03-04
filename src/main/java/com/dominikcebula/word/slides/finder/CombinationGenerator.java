package com.dominikcebula.word.slides.finder;

import com.dominikcebula.word.slides.ds.IndexedSentence;
import com.dominikcebula.word.slides.ds.Range;
import io.vavr.collection.List;
import io.vavr.collection.Stream;
import org.springframework.stereotype.Component;

@Component
class CombinationGenerator {

    List<IndexedSentence> generate(IndexedSentence indexedSentence) {
        return generateAllCombinationsRanges(indexedSentence)
                .map(indexedSentence::subSentence)
                .toList();
    }

    private List<Range> generateAllCombinationsRanges(IndexedSentence indexedSentence) {
        return Stream.range(0, indexedSentence.length() + 1)
                .flatMap(
                        from -> Stream.range(from + 1, indexedSentence.length() + 1)
                                .map(to -> new Range(from, to))
                )
                .toList();
    }
}
