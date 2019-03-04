package com.dominikcebula.word.slides.output;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.PrintStream;
import java.util.Map;
import java.util.stream.Collectors;

import static com.dominikcebula.word.slides.spring.Profiles.CLI;

@Component
@Profile(CLI)
public class CliOutputWriter implements OutputWriter {

    static final String ENTRY_SEPARATOR = "," + System.lineSeparator();

    PrintStream stream = System.out;

    @Override
    public void writeOutputValuedSentences(Map<String, Integer> valuedSentences) {
        String entriesAsString = valuedSentences.entrySet().stream()
                .map(this::entryAsString)
                .collect(Collectors.joining(ENTRY_SEPARATOR));

        stream.print(entriesAsString);
    }

    private String entryAsString(Map.Entry<String, Integer> e) {
        return String.format("\"%s\": %d", e.getKey(), e.getValue());
    }
}
