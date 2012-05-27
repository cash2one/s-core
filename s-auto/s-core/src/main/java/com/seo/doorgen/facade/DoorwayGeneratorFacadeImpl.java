package com.seo.doorgen.facade;

import com.seo.doorgen.context.ContextFactory;
import com.seo.doorgen.context.DoorwayContext;
import com.seo.doorgen.model.DoorwayPage;
import com.seo.doorgen.model.GenerateRequest;
import com.seo.doorgen.render.RenderFacade;
import com.seo.message.AbstractMessageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DoorwayGeneratorFacadeImpl extends AbstractMessageHelper implements DoorwayGeneratorFacade{
    private final static Logger LOGGER = LoggerFactory.getLogger(DoorwayGeneratorFacadeImpl.class);

    private DoorwayComponentFacade doorwayComponentFacade;
    private ContextFactory contextFactory;
    private RenderFacade renderFacade;

    public void setRenderFacade(RenderFacade renderFacade) {
        this.renderFacade = renderFacade;
    }

    public void setContextFactory(ContextFactory contextFactory) {
        this.contextFactory = contextFactory;
    }

    public void setDoorwayComponentFacade(DoorwayComponentFacade doorwayComponentFacade) {
        this.doorwayComponentFacade = doorwayComponentFacade;
    }

    @Override
    public String generate(GenerateRequest request) {
        LOGGER.debug("starting doorway generation");
        addMessage("starting doorway generation");

        DoorwayContext context = contextFactory.createContext(request);

        addMessage("creating pages");
        List<DoorwayPage> doorwayPages = doorwayComponentFacade.createComponentPages(request, context);

        addMessage("rendering pages");
        String doorwayPath = renderFacade.renderPages(doorwayPages, request.getGeneralProperties(), context);

        addMessage("finished generation");
        LOGGER.debug("finished generating doorway to path: {}", doorwayPath);

        return doorwayPath;
    }
}
