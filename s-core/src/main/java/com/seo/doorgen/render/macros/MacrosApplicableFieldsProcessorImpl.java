package com.seo.doorgen.render.macros;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

public class MacrosApplicableFieldsProcessorImpl implements MacrosApplicableFieldsProcessor {
    private final static Logger LOGGER = LoggerFactory.getLogger(MacrosApplicableFieldsProcessorImpl.class);

    private MacrosProcessor macrosProcessor;

    public void setMacrosProcessor(MacrosProcessor macrosProcessor) {
        this.macrosProcessor = macrosProcessor;
    }

    @Override
    public void process(Object object) {
        Class objectClass = object.getClass();

        for (Field field : objectClass.getDeclaredFields()) {
            MacrosApplicable macrosApplicable;
            if ((macrosApplicable = field.getAnnotation(MacrosApplicable.class)) != null) {
                if (field.getType() != String.class) {
                    LOGGER.error("field {} has invalid type");

                    throw new RuntimeException("invalid field type");
                }

                try {
                    field.setAccessible(true);
                    String value = (String) field.get(object);

                    LOGGER.debug("updating field {}", field.getName());

                    String processedValue = macrosProcessor.process(value);

                    field.set(object, escapeFTL(processedValue));
                    field.setAccessible(false);
                } catch (IllegalAccessException e) {
                    LOGGER.error("illegal access exception: {}", e.getMessage());

                    throw new RuntimeException("illegal access exception");
                }
            }
        }
    }

    private String escapeFTL(String text) {
        return String.format("${\"%s\"}", text);
    }
}
