package com.seo.doorgen.context;

import com.seo.doorgen.keyword.KeywordFacade;
import com.seo.doorgen.keyword.KeywordFacadeImpl;
import com.seo.doorgen.model.GenerateRequest;
import com.seo.text.generation.AbstractTextGenerator;
import com.seo.text.generation.facade.TextGeneratorFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContextFactoryImpl implements ContextFactory{
    private final static Logger LOGGER = LoggerFactory.getLogger(ContextFactoryImpl.class);

    private TextGeneratorFacade textGeneratorFacade;

    public void setTextGeneratorFacade(TextGeneratorFacade textGeneratorFacade) {
        this.textGeneratorFacade = textGeneratorFacade;
    }

    @Override
    public DoorwayContext createContext(GenerateRequest request) {
        LOGGER.debug("creating doorway context");

        DoorwayContext context = new DoorwayContext();

        AbstractTextGenerator generator = textGeneratorFacade.getGenerator(request.getGeneralProperties().getTextGeneratorName());
        generator.setSource(request.getTextSource().getContent());
        
        if(!generator.isInitialized()) {
            generator.initialize();
        }


        context.setTextGenerator(
                generator
        );
        context.setKeywordFacade(createKeywordFacade(request));
        context.setUrl(request.getGeneralProperties().getUrl());

        return context;
    }

    private KeywordFacade createKeywordFacade(GenerateRequest request) {
        LOGGER.debug("creating keyword facade");

        KeywordFacadeImpl keywordFacade = new KeywordFacadeImpl();
        keywordFacade.setKeywords(request.getKeywords());

        return keywordFacade;
    }
}
