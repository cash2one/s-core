package com.seo.doorgen.render;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RenderServiceFacadeImpl implements RenderServiceFacade{
    private List<AbstractRenderService> services;

    public void setServices(List<AbstractRenderService> services) {
        this.services = services;
    }

    @Override
    public List<AbstractRenderService> getServices() {
        return this.services;
    }

    @Override
    public Map<String, AbstractRenderService> getServiceMap() {
        Map<String, AbstractRenderService> serviceMap = new HashMap<String, AbstractRenderService>();

        for (AbstractRenderService service : services) {
            serviceMap.put(service.getName(), service);
        }

        return serviceMap;
    }
}
