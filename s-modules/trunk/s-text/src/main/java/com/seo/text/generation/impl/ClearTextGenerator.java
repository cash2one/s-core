package com.seo.text.generation.impl;

import com.seo.random.facade.RandomFacade;
import com.seo.text.generation.AbstractTextGenerator;
import com.seo.text.generation.model.Sentence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ClearTextGenerator extends AbstractTextGenerator {
    private final static Logger LOGGER = LoggerFactory.getLogger(ClearTextGenerator.class);

    private final static String NAME = "clear";

    private RandomFacade randomFacade;

    public void setRandomFacade(RandomFacade randomFacade) {
        this.randomFacade = randomFacade;
    }

    private List<Sentence> sentenceQueue = new ArrayList<Sentence>();

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public Sentence generateSentence() {
        LOGGER.debug("generating clear text sentence");


        final int queueSize = sentenceQueue.size();
        return sentenceQueue.remove(randomFacade.getInteger(queueSize));
    }

    @Override
    public void initialize() {
        if (initialized) {
            LOGGER.error("already initialized, aborting");

            throw new IllegalStateException("already initialized");
        }

        String sourceWithoutLineBreaks = source.replaceAll("[\n\r]+", "");
        String sourceWithSeparators = sourceWithoutLineBreaks.replaceAll("([\\.!?]+)", "$1<>");

        String[] separatedSource = sourceWithSeparators.split("<>");
        for (String sentence : separatedSource) {
            sentenceQueue.add(new Sentence(sentence));
        }
    }
}
