package com.seo.text.generation.keyword;

import com.seo.random.facade.RandomFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KeywordInserterImpl implements KeywordInserter{
    private final static Logger LOGGER = LoggerFactory.getLogger(KeywordInserterImpl.class);

    private RandomFacade randomFacade;

    public void setRandomFacade(RandomFacade randomFacade) {
        this.randomFacade = randomFacade;
    }

    @Override
    public String insertKeyword(String text, String keyword) {
        LOGGER.debug("inserting keyword {} into text", keyword);

        String[] textWords = text.split(" ");
        int wordsCount = textWords.length;

        int randomIndex = randomFacade.getInteger(wordsCount);
        String randomWord = textWords[randomIndex];
        
        String firstWordPattern = String.format("^%s\\s+", Pattern.quote(randomWord));
        Pattern firstPattern = Pattern.compile(firstWordPattern);
        Matcher firstMatcher = firstPattern.matcher(text);

        String middleWordPattern = String.format("\\s+%s\\s+", Pattern.quote(randomWord));
        Pattern middlePattern = Pattern.compile(middleWordPattern);
        Matcher middleMatcher = middlePattern.matcher(text);

        String lastWordPattern = String.format("\\s+%s$", Pattern.quote(randomWord));
        Pattern lastPattern = Pattern.compile(lastWordPattern);
        Matcher lastMatcher = lastPattern.matcher(text);


        String resultText;

        if(firstMatcher.find()) {
            LOGGER.debug("matched first pattern");

            resultText = text.replaceFirst(firstWordPattern, keyword + " ");
        } else if(middleMatcher.find()) {
            LOGGER.debug("matched middle pattern");

            resultText = text.replaceFirst(middleWordPattern, " " + keyword + " ");
        } else if(lastMatcher.find()) {
            LOGGER.debug("matched last pattern");

            resultText = text.replaceFirst(lastWordPattern, " " + keyword);
        } else {
            LOGGER.warn("did not matched any pattern, returning original string");

            resultText = text;
        }

        return resultText;
    }
}
