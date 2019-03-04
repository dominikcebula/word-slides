package com.dominikcebula.word.slides.output;

import java.util.Map;

public interface OutputWriter {
    void writeOutputValuedSentences(Map<String, Integer> valuedSentences);
}
