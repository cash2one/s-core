package com.seo.doorgen.render;

import com.seo.doorgen.context.DoorwayContext;
import com.seo.doorgen.model.DoorwayPage;
import com.seo.doorgen.model.GeneralProperties;

import java.util.List;

public interface RenderFacade {
    /**
     * Renders doorway to disk
     * @param doorwayPages doorway pages to render
     * @param properties render properties
     * @param context
     * @return doorway's root path
     */
    String renderPages(List<DoorwayPage> doorwayPages, GeneralProperties properties, DoorwayContext context);
}
