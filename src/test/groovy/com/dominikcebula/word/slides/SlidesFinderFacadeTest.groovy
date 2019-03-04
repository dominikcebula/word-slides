package com.dominikcebula.word.slides

import com.dominikcebula.word.slides.finder.SlidesFinder
import com.dominikcebula.word.slides.input.InputReader
import com.dominikcebula.word.slides.output.OutputWriter
import spock.lang.Specification

class SlidesFinderFacadeTest extends Specification {

    private static String INPUT_SENTENCE = "example sentence"
    private static Map<String, Integer> VALUED_SENTENCE = [:]

    SlidesFinderFacade slidesFinderFacade = new SlidesFinderFacade()
    InputReader inputReader = Mock()
    OutputWriter outputWriter = Mock()
    SlidesFinder slidesFinder = Mock()

    def setup() {
        slidesFinderFacade.inputReader = inputReader
        slidesFinderFacade.outputWriter = outputWriter
        slidesFinderFacade.slidesFinder = slidesFinder
    }

    def "should read, process and report results"() {
        when:
        slidesFinderFacade.run()

        then:
        1 * inputReader.readInputSentence() >> INPUT_SENTENCE
        1 * slidesFinder.findSlides(INPUT_SENTENCE) >> VALUED_SENTENCE
        1 * outputWriter.writeOutputValuedSentences(VALUED_SENTENCE)
        0 * _
    }
}
