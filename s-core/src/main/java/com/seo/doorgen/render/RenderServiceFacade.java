package com.seo.doorgen.render;

import java.util.List;
import java.util.Map;

public interface RenderServiceFacade {
    List<AbstractRenderService> getServices();
    Map<String, AbstractRenderService> getServiceMap();
}
