package com.dominikcebula.word.slides.comparators;

import com.dominikcebula.word.slides.ds.IndexedSentence;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class IndexedSentenceComparator implements Comparator<IndexedSentence> {
    @Override
    public int compare(IndexedSentence o1, IndexedSentence o2) {
        return o2.length() - o1.length();
    }
}
