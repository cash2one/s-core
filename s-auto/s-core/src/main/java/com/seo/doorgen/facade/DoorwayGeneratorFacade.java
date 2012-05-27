package com.seo.doorgen.facade;

import com.seo.doorgen.model.GenerateRequest;
import com.seo.message.MessageNotifier;

public interface DoorwayGeneratorFacade extends MessageNotifier{
    /**
     * Generates doorway by provided request
     * @param request parameters for doorway generation
     * @return path to the generated doorway root
     */
    String generate(GenerateRequest request);
}
