package com.seo.text.generation;

import com.seo.text.generation.model.Sentence;

import java.util.List;

public interface TextGenerator {
    Sentence generateSentence();
    void initialize();
    List<Sentence> generatePhrase(String key, Integer minLength, Integer maxLength);
    List<Sentence> generatePhrase(Integer minLength, Integer maxLength);
}
