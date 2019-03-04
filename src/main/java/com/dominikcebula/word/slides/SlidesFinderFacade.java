package com.dominikcebula.word.slides;

import com.dominikcebula.word.slides.finder.SlidesFinder;
import com.dominikcebula.word.slides.input.InputReader;
import com.dominikcebula.word.slides.output.OutputWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class SlidesFinderFacade {

    @Autowired
    InputReader inputReader;
    @Autowired
    SlidesFinder slidesFinder;
    @Autowired
    OutputWriter outputWriter;

    void run() {
        String inputSentence = inputReader.readInputSentence();

        var valuedSentences = slidesFinder.findSlides(inputSentence);

        outputWriter.writeOutputValuedSentences(valuedSentences);
    }
}
