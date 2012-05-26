package com.seo.doorgen.keyword;

import com.seo.doorgen.model.Keyword;
import org.easymock.Capture;
import org.testng.annotations.Test;

import java.util.List;

import static org.easymock.EasyMock.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TestKeywordFacadeImpl {
    @Test
    public void testGetRandomKeywordOneWord() {
        List<Keyword> mockKeywords = createMock(List.class);
        expect(mockKeywords.size()).andReturn(1).times(2);
        expect(mockKeywords.get(0)).andReturn(new Keyword("testword"));
        replay(mockKeywords);

        KeywordFacadeImpl keywordFacade = new KeywordFacadeImpl();
        keywordFacade.setKeywords(mockKeywords);

        List<Keyword> keyword = keywordFacade.getRandomKeywords(1);
        assertTrue(keyword.size() == 1);
        assertEquals(keyword.get(0).getContent(), "testword");

        verify(mockKeywords);
    }

    @Test
    public void testGetRandomKeyword() {
        List<Keyword> mockKeywords = createMock(List.class);
        expect(mockKeywords.size()).andReturn(10).times(2);

        Capture<Integer> capturedInteger = new Capture<Integer>();
        expect(mockKeywords.get(capture(capturedInteger))).andReturn(new Keyword("testword"));
        replay(mockKeywords);

        KeywordFacadeImpl keywordFacade = new KeywordFacadeImpl();
        keywordFacade.setKeywords(mockKeywords);

        List<Keyword> keyword = keywordFacade.getRandomKeywords(1);
        assertTrue(keyword.size() == 1);
        assertEquals(keyword.get(0).getContent(), "testword");
        assertTrue(capturedInteger.getValue() < 10);

        verify(mockKeywords);
    }

    @Test
    public void testGetUnusedKeywords() {
        List<Keyword> mockKeywords = createMock(List.class);
        expect(mockKeywords.size()).andReturn(2).times(6);

        Capture<Integer> capturedCount = new Capture<Integer>();
        expect(mockKeywords.get(capture(capturedCount))).andReturn(new Keyword("testword1"));
        expect(mockKeywords.get(capture(capturedCount))).andReturn(new Keyword("testword1"));
        expect(mockKeywords.get(capture(capturedCount))).andReturn(new Keyword("testword1"));
        expect(mockKeywords.get(capture(capturedCount))).andReturn(new Keyword("testword2"));

        replay(mockKeywords);

        KeywordFacadeImpl keywordFacade = new KeywordFacadeImpl();
        keywordFacade.setKeywords(mockKeywords);

        List<Keyword> keywords1 = keywordFacade.getUnusedKeywords(1);
        List<Keyword> keywords2 = keywordFacade.getUnusedKeywords(1);

        assertTrue(keywords1.size() == 1);
        assertEquals(keywords1.get(0).getContent(), "testword1");
        assertTrue(keywords2.size() == 1);
        assertEquals(keywords2.get(0).getContent(), "testword2");

        verify(mockKeywords);
    }
}
