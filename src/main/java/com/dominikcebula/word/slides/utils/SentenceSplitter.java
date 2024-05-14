package com.dominikcebula.word.slides.utils;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class SentenceSplitter {

    public static String[] splitSentence(String sentence) {
        if (!isBlank(sentence)) {
            return sentence.split(" +");
        } else {
            return new String[0];
        }
    }
}
