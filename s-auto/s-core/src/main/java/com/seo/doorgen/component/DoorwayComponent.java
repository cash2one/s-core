package com.seo.doorgen.component;

import com.seo.doorgen.context.DoorwayContext;
import com.seo.doorgen.model.DoorwayPage;
import com.seo.doorgen.model.GenerateRequest;

import java.util.List;

public interface DoorwayComponent {
    List<DoorwayPage> createPages(GenerateRequest request, DoorwayContext context);
}
