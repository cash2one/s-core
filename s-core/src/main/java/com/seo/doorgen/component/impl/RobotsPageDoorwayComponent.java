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

public class RobotsPageDoorwayComponent extends AbstractDoorwayComponent implements DoorwayComponent {
    private final static Logger LOGGER = LoggerFactory.getLogger(RobotsPageDoorwayComponent.class);

    private final static String FILENAME = "robots.txt";

    @Override
    public List<DoorwayPage> createPages(GenerateRequest request, DoorwayContext context) {
        LOGGER.debug("creating robots.txt");

        List<DoorwayPage> robotsPage = new ArrayList<DoorwayPage>();
        robotsPage.add(
                new DoorwayPage(
                        null,
                        FILENAME,
                        null,
                        DoorwayPageType.ROBOTS,
                        null,
                        null
                )
        );

        return robotsPage;
    }
}
