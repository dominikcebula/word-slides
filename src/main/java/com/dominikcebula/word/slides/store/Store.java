package com.dominikcebula.word.slides.store;

public interface Store {
    boolean contains(String slide);

    int getValue(String slide);
}
