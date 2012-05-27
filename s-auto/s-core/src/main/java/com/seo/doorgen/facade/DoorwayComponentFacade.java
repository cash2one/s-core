package com.seo.doorgen.facade;

import com.seo.doorgen.context.DoorwayContext;
import com.seo.doorgen.model.DoorwayPage;
import com.seo.doorgen.model.GenerateRequest;

import java.util.List;

public interface DoorwayComponentFacade {
    List<DoorwayPage> createComponentPages(GenerateRequest request, DoorwayContext context);
}
