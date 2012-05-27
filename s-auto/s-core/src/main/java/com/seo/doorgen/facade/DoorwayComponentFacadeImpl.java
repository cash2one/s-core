package com.seo.doorgen.facade;

import com.seo.doorgen.component.DoorwayComponent;
import com.seo.doorgen.context.DoorwayContext;
import com.seo.doorgen.model.DoorwayPage;
import com.seo.doorgen.model.GenerateRequest;

import java.util.ArrayList;
import java.util.List;

public class DoorwayComponentFacadeImpl implements DoorwayComponentFacade{
    private List<DoorwayComponent> doorwayComponents;

    public void setDoorwayComponents(List<DoorwayComponent> doorwayComponents) {
        this.doorwayComponents = doorwayComponents;
    }

    @Override
    public List<DoorwayPage> createComponentPages(GenerateRequest request, DoorwayContext context) {
        List<DoorwayPage> doorwayPages = new ArrayList<DoorwayPage>();
        
        for (DoorwayComponent doorwayComponent : doorwayComponents) {
            List<DoorwayPage> pages = doorwayComponent.createPages(request, context);

            doorwayPages.addAll(pages);
            context.getDoorwayPages().addAll(pages);
        }

        return doorwayPages;
    }
}