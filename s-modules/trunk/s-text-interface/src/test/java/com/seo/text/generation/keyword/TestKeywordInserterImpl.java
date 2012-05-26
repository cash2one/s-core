package com.seo.text.generation.keyword;

import com.seo.random.facade.RandomFacade;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.easymock.EasyMock.*;
import static org.testng.Assert.assertEquals;


public class TestKeywordInserterImpl {
    private KeywordInserterImpl keywordInserter;
    private RandomFacade mockRandomFacade;

    @BeforeMethod
    public void init() {
        keywordInserter = new KeywordInserterImpl();
        mockRandomFacade = createMock(RandomFacade.class);

        keywordInserter.setRandomFacade(mockRandomFacade);
    }

    @Test
    public void testInserter() {
        reset(mockRandomFacade);
        expect(mockRandomFacade.getInteger(5)).andReturn(1);
        expect(mockRandomFacade.getInteger(5)).andReturn(0);
        expect(mockRandomFacade.getInteger(5)).andReturn(4);
        replay(mockRandomFacade);
        
        String text = "This is a test string";
        String word = "keyword";

        String result = keywordInserter.insertKeyword(text, word);
        assertEquals("This keyword a test string", result);

        String result2 = keywordInserter.insertKeyword(text, word);
        assertEquals("keyword is a test string", result2);

        String result3 = keywordInserter.insertKeyword(text, word);
        assertEquals("This is a test keyword", result3);

        verify(mockRandomFacade);
    }
}
