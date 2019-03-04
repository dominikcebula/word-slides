package com.dominikcebula.word.slides.ds;

import com.dominikcebula.word.slides.utils.SentenceSplitter;
import io.vavr.collection.List;
import io.vavr.collection.Stream;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import static org.apache.commons.lang3.StringUtils.SPACE;

@Getter
@EqualsAndHashCode
public class IndexedSentence {
    private final List<IndexedWord> indexedWords;

    IndexedSentence(List<IndexedWord> indexedWords) {
        this.indexedWords = indexedWords;
    }

    public IndexedSentence subSentence(Range range) {
        return new IndexedSentence(
                indexedWords.subSequence(range.getFrom(), range.getTo())
        );
    }

    public String asString() {
        return indexedWords.map(IndexedWord::getWord)
                .reduceOption((a, b) -> String.join(SPACE, a, b))
                .getOrElse(StringUtils.EMPTY);
    }

    public int length() {
        return indexedWords.length();
    }

    @Override
    public String toString() {
        return asString();
    }

    static IndexedSentence createEmptySentence() {
        return createIndexedSentence(StringUtils.EMPTY);
    }

    public static IndexedSentence createIndexedSentence(String sentence) {

        return new IndexedSentence(
                Stream.of(SentenceSplitter.splitSentence(sentence))
                        .zipWithIndex()
                        .map(t -> new IndexedWord(t._1, t._2))
                        .toList()
        );
    }
}
