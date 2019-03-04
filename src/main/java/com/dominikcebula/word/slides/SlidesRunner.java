package com.dominikcebula.word.slides;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
class SlidesRunner implements ApplicationRunner {

    @Autowired
    private SlidesFinderFacade slidesFinderFacade;

    public static void main(String... args) {
        SpringApplication.run(SlidesRunner.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        slidesFinderFacade.run();
    }
}
