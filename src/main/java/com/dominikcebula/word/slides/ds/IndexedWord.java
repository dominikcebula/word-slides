package com.dominikcebula.word.slides.ds;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class IndexedWord {
    private String word;
    private int index;
}
