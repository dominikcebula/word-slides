package com.dominikcebula.word.slides.input

import org.springframework.boot.ApplicationArguments
import spock.lang.Specification

class CliInputReaderTest extends Specification {
    CliInputReader cliInputReader = new CliInputReader()
    ApplicationArguments applicationArguments = Mock()

    def setup() {
        cliInputReader.applicationArguments = applicationArguments
    }

    def "should read empty input arguments correctly"() {
        given:
        applicationArguments.getSourceArgs() >> []

        when:
        def sentence = cliInputReader.readInputSentence()

        then:
        sentence.length() == 0
    }

    def "should read one argument correctly"() {
        given:
        applicationArguments.getSourceArgs() >> ["abc"]

        when:
        def sentence = cliInputReader.readInputSentence()

        then:
        sentence == "abc"
    }

    def "should read three argument correctly"() {
        given:
        applicationArguments.getSourceArgs() >> ["abc", "def", "xyz"]

        when:
        def sentence = cliInputReader.readInputSentence()

        then:
        sentence == "abc def xyz"
    }
}
