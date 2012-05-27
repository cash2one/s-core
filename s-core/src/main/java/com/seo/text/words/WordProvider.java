package com.seo.text.words;

public interface WordProvider {
    String getRandomWord(int minsize, int maxsize);
    String getRandomWordWithDigits(int min, int max);    
}
