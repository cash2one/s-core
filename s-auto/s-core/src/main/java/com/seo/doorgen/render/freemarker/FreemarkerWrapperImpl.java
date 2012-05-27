package com.seo.doorgen.render.freemarker;

import com.seo.doorgen.context.DoorwayContext;
import com.seo.doorgen.model.DoorwayPage;
import com.seo.doorgen.model.Template;
import com.seo.doorgen.render.RenderServiceFacade;
import com.seo.doorgen.render.macros.MacrosProcessor;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FreemarkerWrapperImpl implements FreemarkerWrapper, InitializingBean {
    private final static Logger LOGGER = LoggerFactory.getLogger(FreemarkerWrapperImpl.class);

    private final static String PAGE_NAME = "page";
    private final static String CONTEXT_NAME = "context";
    private final static String CHARSET_NAME = "charset";
    private RenderServiceFacade renderServiceFacade;

    public void setRenderServiceFacade(RenderServiceFacade renderServiceFacade) {
        this.renderServiceFacade = renderServiceFacade;
    }

    private Configuration configuration;

    private MacrosProcessor macrosProcessor;

    public void setMacrosProcessor(MacrosProcessor macrosProcessor) {
        this.macrosProcessor = macrosProcessor;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        LOGGER.debug("initializing freemarker configuration");
        this.configuration = new Configuration();
    }

    @Override
    public void renderPage(DoorwayPage doorwayPage, Template template, String doorwayDirectory, String extension, String encoding, DoorwayContext context) {
        LOGGER.debug("rendering page {} with freemarker, using template {}", doorwayPage.getFilename(), template.getType());


        freemarker.template.Template freemarkerTemplate;
        try {
            String temlateData = template.getContent();
            String templateSubstituted = macrosProcessor.process(temlateData);

            freemarkerTemplate = new freemarker.template.Template(
                    template.getType().toString(),
                    new StringReader(templateSubstituted),
                    this.configuration
            );

            File doorwayDirectoryFile = new File(doorwayDirectory);
            if (!doorwayDirectoryFile.exists()) {
                LOGGER.error("doorway directory {} not exists", doorwayDirectory);

                throw new RuntimeException("doorway directory does not exist: " + doorwayDirectory);
            } else if (!doorwayDirectoryFile.isDirectory()) {
                LOGGER.error("doorway root {} is not a directory", doorwayDirectory);

                throw new RuntimeException("doorway root is not a directory: " + doorwayDirectory);
            }

            File outputFile = new File(doorwayDirectoryFile, doorwayPage.getFilename());
            LOGGER.debug("absolute path: {}", outputFile.getAbsolutePath());

            Map<String, Object> dataModel = new HashMap<String, Object>();
            dataModel.putAll(renderServiceFacade.getServiceMap());
            dataModel.put(PAGE_NAME, doorwayPage);
            dataModel.put(CONTEXT_NAME, context);
            dataModel.put(CHARSET_NAME, encoding);

            freemarkerTemplate.process(
                    dataModel,
                    new OutputStreamWriter(new FileOutputStream(outputFile), encoding)
            );
        } catch (IOException e) {
            LOGGER.error("i/o error: {}", e.getMessage());

            throw new RuntimeException("i/o error: {}" + e.getMessage());
        } catch (TemplateException e) {
            LOGGER.error("template error: {}", e.getMessage());

            throw new RuntimeException("template error: " + e.getMessage());
        }


    }

    private String getFilePath(String fileName, String doorwayDirectory) {
        return doorwayDirectory + fileName;
    }
}
