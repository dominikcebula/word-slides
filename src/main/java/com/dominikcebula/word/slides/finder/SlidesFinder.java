package com.dominikcebula.word.slides.finder;

import com.dominikcebula.word.slides.comparators.IndexedSentenceComparator;
import com.dominikcebula.word.slides.ds.CumulativeConsumedWords;
import com.dominikcebula.word.slides.ds.IndexedSentence;
import com.dominikcebula.word.slides.ds.ValuedIndexedSentence;
import com.dominikcebula.word.slides.store.Store;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.collection.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class SlidesFinder {

    @Autowired
    private CombinationGenerator combinationGenerator;
    @Autowired
    private IndexedSentenceComparator indexedSentenceComparator;
    @Autowired
    private CumulativeConsumedWordsAppender cumulativeConsumedWordsAppender;
    @Autowired
    private Store store;

    public Map<String, Integer> findSlides(String sentence) {
        var allCombinationSentences = getSortedAllSentenceCombinations(sentence);
        var allSentencesExistingInStore = getAllSentencesExistingInStore(allCombinationSentences);
        var allSentencesOptimalAndPossibleForConsumption = getAllSentencesOptimalAndPossibleForConsumption(allSentencesExistingInStore);

        return getValuedSentencesMap(allSentencesOptimalAndPossibleForConsumption);
    }

    private List<IndexedSentence> getSortedAllSentenceCombinations(String sentence) {
        return Stream.of(sentence)
                .map(this::toIndexedSentence)
                .flatMap(this::generateAllSlidesCombinations)
                .sorted(byLongestSentence())
                .toList();
    }

    private List<IndexedSentence> getAllSentencesExistingInStore(List<IndexedSentence> allCombinationSentences) {
        return List.ofAll(
                allCombinationSentences.toJavaParallelStream()
                        .filter(this::toOnlySentencesPresentInStore)
                        .collect(Collectors.toList())
        );
    }

    private List<IndexedSentence> getAllSentencesOptimalAndPossibleForConsumption(List<IndexedSentence> allSentencesExistingInStore) {
        return allSentencesExistingInStore
                .scanLeft(new CumulativeConsumedWords(), cumulativeConsumedWordsAppender::append)
                .filter(CumulativeConsumedWords::sentenceNotEmpty)
                .filter(CumulativeConsumedWords::noConsumptionConflict)
                .map(CumulativeConsumedWords::getParentSentence)
                .toList();
    }

    private Map<String, Integer> getValuedSentencesMap(List<IndexedSentence> allSentencesOptimalAndPossibleForConsumption) {
        return List.ofAll(allSentencesOptimalAndPossibleForConsumption
                .toJavaParallelStream()
                .map(this::assignValueFromStoreToSentence)
                .collect(Collectors.toList()))
                .toLinkedMap(this::valuedIndexedSentenceToMapElement)
                .toJavaMap();
    }

    private IndexedSentence toIndexedSentence(String sentence) {
        return IndexedSentence.createIndexedSentence(sentence);
    }

    private List<IndexedSentence> generateAllSlidesCombinations(IndexedSentence indexedSentence) {
        return combinationGenerator.generate(indexedSentence);
    }

    private Comparator<IndexedSentence> byLongestSentence() {
        return indexedSentenceComparator;
    }

    private boolean toOnlySentencesPresentInStore(IndexedSentence indexedSentence) {
        return store.contains(indexedSentence.asString());
    }

    private ValuedIndexedSentence assignValueFromStoreToSentence(IndexedSentence indexedSentence) {
        return new ValuedIndexedSentence(indexedSentence, store.getValue(indexedSentence.asString()));
    }

    private Tuple2<String, Integer> valuedIndexedSentenceToMapElement(ValuedIndexedSentence valuedIndexedSentence) {
        return new Tuple2<>(valuedIndexedSentence.getIndexedSentence().asString(), valuedIndexedSentence.getValue());
    }
}
