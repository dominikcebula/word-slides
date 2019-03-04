package com.dominikcebula.word.slides.utils;

public class SentenceSplitter {

    public static String[] splitSentence(String sentence) {
        var splits = sentence.split(" +");

        if (splits.length == 1 && splits[0].equals(""))
            return new String[0];
        else
            return splits;
    }
}
