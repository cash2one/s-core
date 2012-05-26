package com.seo.doorgen.component.impl;

import com.seo.doorgen.component.AbstractDoorwayComponent;
import com.seo.doorgen.component.DoorwayComponent;
import com.seo.doorgen.context.DoorwayContext;
import com.seo.doorgen.model.DoorwayPage;
import com.seo.doorgen.model.DoorwayPageType;
import com.seo.doorgen.model.GenerateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class SitemapPageDoorwayComponent extends AbstractDoorwayComponent implements DoorwayComponent{
    private final static Logger LOGGER = LoggerFactory.getLogger(SitemapPageDoorwayComponent.class);

    private final static String SITEMAP_XML_DEFAULT_NAME = "sitemap.xml";
    private final static String SITEMAP_HTML_DEFAULT_NAME = "sitemap";


    @Override
    public List<DoorwayPage> createPages(GenerateRequest request, DoorwayContext context) {
        LOGGER.debug("generation sitemaps");

        List<DoorwayPage> contentPages = new ArrayList<DoorwayPage>();
        for (DoorwayPage doorwayPage : context.getDoorwayPages()) {
            if(doorwayPage.getPageType() == DoorwayPageType.CONTENT) {
                contentPages.add(doorwayPage);
            }
        }

        List<DoorwayPage> sitemaps = new ArrayList<DoorwayPage>();
        sitemaps.add(
                new DoorwayPage(
                        null,
                        SITEMAP_XML_DEFAULT_NAME,
                        null,
                        DoorwayPageType.SITEMAP_XML,
                        null,
                        contentPages
                )
        );
        sitemaps.add(
                new DoorwayPage(
                        null,
                        concatenateFilename(SITEMAP_HTML_DEFAULT_NAME, request.getGeneralProperties().getFilesExtension()),
                        null,
                        DoorwayPageType.SITEMAP,
                        null,
                        contentPages
                )
        );

        return sitemaps;
    }
}
