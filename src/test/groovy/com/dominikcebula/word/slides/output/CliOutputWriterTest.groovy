package com.dominikcebula.word.slides.output

import spock.lang.Specification

import static com.dominikcebula.word.slides.output.CliOutputWriter.ENTRY_SEPARATOR

class CliOutputWriterTest extends Specification {

    CliOutputWriter cliOutputWriter = new CliOutputWriter()

    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()
    PrintStream printStream = new PrintStream(byteArrayOutputStream)

    def setup() {
        cliOutputWriter.stream = printStream
    }

    def cleanup() {
        printStream.close()
        byteArrayOutputStream.close()
    }

    def "should write nothing when empty values sentences provided"() {
        when:
        cliOutputWriter.writeOutputValuedSentences([:])

        then:
        byteArrayOutputStream.toString().isEmpty()
    }

    def "should write valued sentences"() {
        when:
        cliOutputWriter.writeOutputValuedSentences(["sentence one": 5, "sentence two": 7])

        then:
        byteArrayOutputStream.toString() == "\"sentence one\": 5" + ENTRY_SEPARATOR + "\"sentence two\": 7"
    }
}
