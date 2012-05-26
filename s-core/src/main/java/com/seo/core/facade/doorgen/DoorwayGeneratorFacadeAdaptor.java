package com.seo.core.facade.doorgen;

import com.seo.core.model.DoorwayConfiguration;
import com.seo.core.model.KeywordGroup;
import com.seo.core.model.Template;
import com.seo.core.model.TextSource;
import com.seo.message.MessageNotifier;

public interface DoorwayGeneratorFacadeAdaptor extends MessageNotifier{
    /**
     * Adaptor to doorway generator module
     * @param configuration doorway configuration
     * @param template doorway template
     * @param keywordGroup doorway keywords
     * @param textSource doorway text generation source
     * @param url
     * @return path to doorway root
     */
    String createDoorway(DoorwayConfiguration configuration, Template template, KeywordGroup keywordGroup, TextSource textSource, String url);
}
