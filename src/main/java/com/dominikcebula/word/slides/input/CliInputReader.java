package com.dominikcebula.word.slides.input;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import static com.dominikcebula.word.slides.spring.Profiles.CLI;

@Component
@Profile(CLI)
public class CliInputReader implements InputReader {

    @Autowired
    ApplicationArguments applicationArguments;

    @Override
    public String readInputSentence() {
        return String.join(StringUtils.SPACE, applicationArguments.getSourceArgs());
    }
}
