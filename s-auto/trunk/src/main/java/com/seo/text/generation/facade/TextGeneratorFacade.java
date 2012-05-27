package com.seo.text.generation.facade;

import com.seo.text.generation.AbstractTextGenerator;

import java.util.List;

public interface TextGeneratorFacade {
    AbstractTextGenerator getGenerator(String name);
    List<String> getGeneratorNames();
}
