package com.seo.doorgen.component.impl;

import com.seo.doorgen.component.AbstractDoorwayComponent;
import com.seo.doorgen.component.DoorwayComponent;
import com.seo.doorgen.context.DoorwayContext;
import com.seo.doorgen.keyword.KeywordFacade;
import com.seo.doorgen.model.*;
import com.seo.doorgen.model.component.CategoryPageProperties;
import com.seo.random.facade.RandomFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class CategoryPageDoorwayComponent extends AbstractDoorwayComponent implements DoorwayComponent{
    private final static Logger LOGGER = LoggerFactory.getLogger(CategoryPageDoorwayComponent.class);

    private final static int DEFAULT_KEYWORD_COUNT = 1;

    private RandomFacade randomFacade;

    public void setRandomFacade(RandomFacade randomFacade) {
        this.randomFacade = randomFacade;
    }

    @Override
    public List<DoorwayPage> createPages(GenerateRequest request, DoorwayContext context) {
        LOGGER.debug("creating category pages");

        List<DoorwayPage> categoryPages = populatePageList(request.getCategoryPageProperties(), request.getGeneralProperties(), context);

        Queue<DoorwayPage> doorwayPagesQueue = new ArrayDeque<DoorwayPage>(context.getDoorwayPages());
        while(doorwayPagesQueue.size() > 0) {
            DoorwayPage categoryPage = chooseRandomCategoryPage(categoryPages);

            DoorwayPage doorwayPage = doorwayPagesQueue.poll();
            if(doorwayPage.getPageType() != DoorwayPageType.CONTENT) {
                //skipping non content page
                continue;
            }

            assert categoryPage.getLinkedPages() !=null;
            categoryPage.getLinkedPages().add(doorwayPage);
        }

        return categoryPages;
    }

    private List<DoorwayPage> populatePageList(CategoryPageProperties properties, GeneralProperties generalProperties, DoorwayContext context) {
        List<DoorwayPage> doorwayPages = new ArrayList<DoorwayPage>();

        KeywordFacade keywordFacade = context.getKeywordFacade();
        int numberOfPages = randomFacade.getIntFromRange(properties.getMinNumberOfPages(), properties.getMaxNumberOfPages());
        for(int pageIndex = 0; pageIndex < numberOfPages; pageIndex++) {
            List<Keyword> keywords = keywordFacade.getRandomKeywords(DEFAULT_KEYWORD_COUNT);
            doorwayPages.add(
                    new DoorwayPage(
                            getRandomKeyword(keywords).getContent(),
                            concatenateFilename("category_page_" + pageIndex, generalProperties.getFilesExtension()),
                            new ArrayList<String>(),
                            DoorwayPageType.CATEGORY,
                            keywords,
                            new ArrayList<DoorwayPage>()
                    )
            );
        }

        return doorwayPages;
    }

    private DoorwayPage chooseRandomCategoryPage(List<DoorwayPage> categoryPages) {
        assert categoryPages != null && categoryPages.size() > 0;

        int pageIndex = randomFacade.getInteger(categoryPages.size());

        return categoryPages.get(pageIndex);
    }
}
