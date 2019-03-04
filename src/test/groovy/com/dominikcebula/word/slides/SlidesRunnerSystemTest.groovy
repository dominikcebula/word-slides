package com.dominikcebula.word.slides

import com.dominikcebula.word.slides.store.StaticStoreForTesting
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import spock.lang.Specification

class SlidesRunnerSystemTest extends Specification {

    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()
    PrintStream printStream = new PrintStream(byteArrayOutputStream)

    PrintStream stdOut

    def setup() {
        stdOut = System.out
        System.setOut(printStream)
    }

    def cleanup() {
        printStream.close()
        byteArrayOutputStream.close()
        System.setOut(stdOut)
    }

    def "should read, process and report simple sentence"() {
        when:
        SlidesRunner.main("Mary went to her house with her friends")

        then:
        byteArrayOutputStream.toString().contains("\"Mary went to her house\": 1")
        byteArrayOutputStream.toString().contains("\"her friends\": 2")
    }

    @Configuration
    static class TestContextConfiguration {

        @Autowired
        void configureStaticStore(StaticStoreForTesting storeForTesting) {
            storeForTesting.setSlidesValues(["Mary went to her house": 1, "her friends": 2])
        }
    }
}
