package com.dominikcebula.word.slides.ds;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ValuedIndexedSentence {
    private IndexedSentence indexedSentence;
    private int value;
}
