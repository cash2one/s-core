package com.seo.text.words;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import javax.inject.Named;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Named
public class TextFileWordProvider extends AbstractWordProvider implements WordProvider, InitializingBean{
    private final static Logger LOGGER = LoggerFactory.getLogger(TextFileWordProvider.class);

    private final static String WORDS_FILE_PATH = "database/words.txt";

    private List<String> words = new ArrayList<String>();
    private Random random = new Random();

    private void init() {
        try {
            BufferedReader input =  readFile();
            String line = null;
            while (( line = input.readLine()) != null) {
                words.add(line);
            }
        } catch(FileNotFoundException e) {
            LOGGER.error("File not found: " + e);

            throw new RuntimeException("File not found: " + e);
        } catch (IOException e) {
            LOGGER.error("IOexception: " + e);

            throw new RuntimeException("IOexception: " + e);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }

    private String randomWord() {
        String word = words.get(random.nextInt(words.size()));

        return toTranslit(word);
    }


    private BufferedReader readFile() throws FileNotFoundException {
        BufferedReader input =  new BufferedReader(
                new InputStreamReader(
                        TextFileWordProvider.class.getClassLoader().getResourceAsStream(WORDS_FILE_PATH)
                )
        );
        return input;
    }
    @Override
    public String getRandomWord(int minsize, int maxsize) {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("getting random word: " + minsize + " to " + maxsize);
        }

        String result = "";
        while(result.length() < minsize) {
            result = result + randomWord();
            result = result.replaceAll(" ", "");
        }

        if(result.length() > maxsize) {
            result = result.substring(0, maxsize);
        }

        return result;
    }

    @Override
    public String getRandomWordWithDigits(int min, int max) {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("getting random word with digits: " + min + " to " + max);
        }

        String result = getRandomWord(min, max);

        return result.substring(0, result.length() - 2) + "1"; //todo: refactor this
    }
}
