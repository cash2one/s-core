package com.seo.doorgen.render.services;

import com.seo.doorgen.model.DoorwayPage;
import com.seo.doorgen.render.AbstractRenderService;

import java.util.List;

public class ContentRenderService extends AbstractRenderService{
    private final static String NAME = "contentService";

    @Override
    public String getName() {
        return NAME;
    }

    public String getRandomContent(DoorwayPage page) {
        List<String> contentList = page.getContents();

        return contentList.get(getRandomFacade().getInteger(contentList.size()));
    }
}
