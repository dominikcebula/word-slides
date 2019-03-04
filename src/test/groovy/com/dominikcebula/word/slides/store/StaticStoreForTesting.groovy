package com.dominikcebula.word.slides.store

class StaticStoreForTesting implements Store {

    private Map<String, Integer> slidesValues = [:]

    def setSlidesValues(Map<String, Integer> slidesValues) {
        this.slidesValues = slidesValues
    }

    @Override
    boolean contains(String slide) {
        return slidesValues.containsKey(slide)
    }

    @Override
    int getValue(String slide) {
        return slidesValues[slide]
    }
}
