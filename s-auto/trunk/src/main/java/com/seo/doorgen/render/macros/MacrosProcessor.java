package com.seo.doorgen.render.macros;

public interface MacrosProcessor {
    /**
     * processes input text replacing macros into freemarker language
     * @param text
     * @return
     */
    String process(String text);
}
