package com.dominikcebula.word.slides.store;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Component;

@Component
public class RandomStore implements Store {

    @Override
    public boolean contains(String slide) {
        return RandomUtils.nextBoolean();
    }

    @Override
    public int getValue(String slide) {
        return RandomUtils.nextInt(1, 11);
    }
}
