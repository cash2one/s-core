package com.seo.doorgen.render.services;

import com.seo.doorgen.context.DoorwayContext;
import com.seo.doorgen.model.DoorwayPage;
import com.seo.doorgen.model.DoorwayPageType;
import com.seo.doorgen.model.Keyword;
import com.seo.doorgen.render.AbstractRenderService;

import java.util.ArrayList;
import java.util.List;

public class PageRenderService extends AbstractRenderService{
    private final static String NAME = "pageService";

    @Override
    public String getName() {
        return NAME;
    }

    public String randomKeyword(DoorwayPage page) {
        Keyword keyword = super.getRandomKeyword(page.getKeywords());

        return keyword == null ? null : keyword.getContent();
    }

    public List<DoorwayPage> getCategoryPages(DoorwayContext context) {
        List<DoorwayPage> pages = new ArrayList<DoorwayPage>();

        for (DoorwayPage doorwayPage : context.getDoorwayPages()) {
            if(doorwayPage.getPageType() == DoorwayPageType.CATEGORY) {
                pages.add(doorwayPage);
            }
        }

        return pages;
    }

    public List<DoorwayPage> getContentPages(DoorwayContext context) {
        List<DoorwayPage> pages = new ArrayList<DoorwayPage>();

        for (DoorwayPage doorwayPage : context.getDoorwayPages()) {
            if(doorwayPage.getPageType() == DoorwayPageType.CONTENT) {
                pages.add(doorwayPage);
            }
        }

        return pages;
    }

    public DoorwayPage getRandomContentPage(DoorwayContext context) {
        List<DoorwayPage> contentPages = getContentPages(context);

        return contentPages.get(getRandomFacade().getInteger(contentPages.size()));
    }
}
