package com.seo.auto.command.mods.extract.impl;

import com.seo.auto.client.registry.Registry;
import com.seo.auto.command.mods.extract.ExtractCommand;
import com.seo.auto.command.mods.extract.Extractor;

/**
 * Extract command executor
 */
public class ExtractorImpl implements Extractor {
    private String source;
    private Registry registry;

    public ExtractorImpl(String source, Registry registry) {
        this.source = source;
        this.registry = registry;
    }

    @Override
    public boolean extract(ExtractCommand extractCommand) {
        return extractCommand.extract(source, registry);
    }
}
