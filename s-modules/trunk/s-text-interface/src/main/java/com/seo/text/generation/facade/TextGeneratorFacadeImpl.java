package com.seo.text.generation.facade;

import com.seo.text.generation.AbstractTextGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class TextGeneratorFacadeImpl implements TextGeneratorFacade{
    private final static Logger LOGGER = LoggerFactory.getLogger(TextGeneratorFacadeImpl.class);

    private List<AbstractTextGenerator> textGenerators;

    public List<AbstractTextGenerator> getTextGenerators() {
        return textGenerators;
    }

    public void setTextGenerators(List<AbstractTextGenerator> textGenerators) {
        this.textGenerators = textGenerators;
    }

    @Override
    public AbstractTextGenerator getGenerator(String name) {
        for (AbstractTextGenerator textGenerator : textGenerators) {
            if(textGenerator.getName().equals(name)) {               
                return textGenerator;
            }
        }

        LOGGER.error("no such text generator: " + name);
        throw new IllegalArgumentException("no such text generator: " + name);
    }

    @Override
    public List<String> getGeneratorNames() {
        List<String> generatorNames = new ArrayList<String>();
        for (AbstractTextGenerator generator : textGenerators) {
            generatorNames.add(generator.getName());
        }

        return generatorNames;
    }
}
