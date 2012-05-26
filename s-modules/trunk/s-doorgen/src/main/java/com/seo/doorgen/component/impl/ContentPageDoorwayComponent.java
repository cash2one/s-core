package com.seo.doorgen.component.impl;

import com.seo.doorgen.component.AbstractDoorwayComponent;
import com.seo.doorgen.component.DoorwayComponent;
import com.seo.doorgen.component.naming.PageNamingStrategy;
import com.seo.doorgen.context.DoorwayContext;
import com.seo.doorgen.keyword.KeywordFacade;
import com.seo.doorgen.model.DoorwayPage;
import com.seo.doorgen.model.DoorwayPageType;
import com.seo.doorgen.model.GenerateRequest;
import com.seo.doorgen.model.Keyword;
import com.seo.doorgen.model.component.ContentPageProperties;
import com.seo.random.facade.RandomFacade;
import com.seo.text.generation.TextGenerator;
import com.seo.text.generation.model.Sentence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ContentPageDoorwayComponent extends AbstractDoorwayComponent implements DoorwayComponent{
    private final static Logger LOGGER = LoggerFactory.getLogger(ContentPageDoorwayComponent.class);

    private final static Integer NUMBER_OF_KEYWORDS_PER_PAGE = 1;//todo: make configurable

    private PageNamingStrategy pageNamingStrategy;
    private RandomFacade randomFacade;
    private KeywordFacade keywordFacade;

    public void setKeywordFacade(KeywordFacade keywordFacade) {
        this.keywordFacade = keywordFacade;
    }

    public void setRandomFacade(RandomFacade randomFacade) {
        this.randomFacade = randomFacade;
    }

    public void setPageNamingStrategy(PageNamingStrategy pageNamingStrategy) {
        this.pageNamingStrategy = pageNamingStrategy;
    }

    @Override
    public List<DoorwayPage> createPages(GenerateRequest request, DoorwayContext context) {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("generating content pages");
        }

        ContentPageProperties properties = request.getContentPageProperties();

        KeywordFacade keywordFacade = context.getKeywordFacade();
        List<DoorwayPage> contentPages = new ArrayList<DoorwayPage>();
        int numberOfPages = randomFacade.getIntFromRange(properties.getMinNumberOfPages(), properties.getMaxNumberOfPages());
        for(int pageIndex = 0; pageIndex < numberOfPages; pageIndex++) {
            List<Keyword> keywords = keywordFacade.getUnusedKeywords(NUMBER_OF_KEYWORDS_PER_PAGE);

            List<String> texts = new ArrayList<String>();
            int numberOfContents = randomFacade.getIntFromRange(properties.getMinNumberOfContents(), properties.getMaxNumberOfContents());
            for(int contentIndex = 0; contentIndex < numberOfContents; contentIndex++) {
                Keyword keyword = keywordFacade.highlightKeyword(getRandomKeyword(keywords));

                TextGenerator textGenerator = context.getTextGenerator();
                List<Sentence> sentences = textGenerator.generatePhrase(keyword.getContent(), properties.getMinContentLength(), properties.getMaxContentLength());

                StringBuffer text = new StringBuffer();
                for (Sentence sentence : sentences) {
                    text.append(sentence.getContent()).append(" ");
                }

                texts.add(text.toString().trim());
            }

            String fileName = pageNamingStrategy.generatePageName(keywords);
            DoorwayPage contentPage = new DoorwayPage(
                    properties.getTitle(),
                    concatenateFilename(fileName, request.getGeneralProperties().getFilesExtension()),
                    texts,
                    DoorwayPageType.CONTENT,
                    keywords,
                    null
            );
            contentPages.add(contentPage);
        }

        return contentPages;
    }
}
