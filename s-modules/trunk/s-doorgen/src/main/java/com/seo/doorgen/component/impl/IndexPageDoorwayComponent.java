package com.seo.doorgen.component.impl;

import com.seo.doorgen.component.AbstractDoorwayComponent;
import com.seo.doorgen.component.DoorwayComponent;
import com.seo.doorgen.context.DoorwayContext;
import com.seo.doorgen.model.DoorwayPage;
import com.seo.doorgen.model.DoorwayPageType;
import com.seo.doorgen.model.GenerateRequest;
import com.seo.doorgen.model.Keyword;
import com.seo.doorgen.model.component.IndexPageProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class IndexPageDoorwayComponent extends AbstractDoorwayComponent implements DoorwayComponent{
    private final static Logger LOGGER = LoggerFactory.getLogger(IndexPageDoorwayComponent.class);

    private final static String INDEX_PAGE_FILENAME = "index";

    @Override
    public List<DoorwayPage> createPages(GenerateRequest request, DoorwayContext context) {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("generating index page");
        }

        IndexPageProperties properties = request.getIndexPageProperties();
        DoorwayPage indexPage = new DoorwayPage(
                properties.getTitle(),
                concatenateFilename(INDEX_PAGE_FILENAME, request.getGeneralProperties().getFilesExtension()),
                null,
                DoorwayPageType.INDEX,
                new ArrayList<Keyword>(),
                null
        );

        List<DoorwayPage> doorwayPages = new ArrayList<DoorwayPage>();
        doorwayPages.add(indexPage);

        return doorwayPages;
    }
}
