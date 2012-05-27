package com.seo.auto.command.mods.extract;

import com.seo.auto.client.registry.Registry;

/**
 * Extract interface 
 */
public interface ExtractCommand {
    public boolean extract(String source, Registry registry);
}
