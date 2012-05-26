package com.seo.text.generation;

import com.seo.text.generation.keyword.KeywordInserter;
import com.seo.text.generation.model.Sentence;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class AbstractTextGenerator implements TextGenerator{
    private final static Random RANDOM = new Random();

    private KeywordInserter keywordInserter;

    public void setKeywordInserter(KeywordInserter keywordInserter) {
        this.keywordInserter = keywordInserter;
    }

    public abstract String getName();

    protected String source;
    protected boolean initialized = false;

    public String getSource() {
        return source;
    }

    public boolean isInitialized() {
        return initialized;
    }

    public void setInitialized(boolean initialized) {
        this.initialized = initialized;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<Sentence> generatePhrase(String key, Integer minLength, Integer maxLength) {
        List<Sentence> sentences = generatePhrase(minLength, maxLength);

        Sentence randomSentence = sentences.get(RANDOM.nextInt(sentences.size()));

        String text = randomSentence.getContent();
        randomSentence.setContent(keywordInserter.insertKeyword(text, key));

        return sentences;
    }

    public List<Sentence> generatePhrase(Integer minLength, Integer maxLength) {
        List<Sentence> sentences = new ArrayList<Sentence>();

        int phraseLength = 0;
        while(phraseLength < minLength) {
            Sentence sentence = generateSentence();
            phraseLength = phraseLength + sentence.getContent().length();

            sentences.add(sentence);
        }

        return sentences;
    }
}
