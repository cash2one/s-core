package com.seo.doorgen.render.freemarker;

import com.seo.doorgen.context.DoorwayContext;
import com.seo.doorgen.model.DoorwayPage;
import com.seo.doorgen.model.Template;

public interface FreemarkerWrapper {
    void renderPage(DoorwayPage doorwayPage, Template template, String doorwayDirectory, String extension, String encoding, DoorwayContext context);
}
