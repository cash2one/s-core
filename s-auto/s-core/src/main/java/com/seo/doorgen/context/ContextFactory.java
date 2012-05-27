package com.seo.doorgen.context;

import com.seo.doorgen.model.GenerateRequest;

public interface ContextFactory {
    DoorwayContext createContext(GenerateRequest request);
}
