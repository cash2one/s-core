package com.seo.doorgen.render;

import com.seo.doorgen.context.DoorwayContext;
import com.seo.doorgen.model.DoorwayPage;
import com.seo.doorgen.model.GeneralProperties;
import com.seo.doorgen.render.freemarker.FreemarkerWrapper;
import com.seo.doorgen.render.media.MediaExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;
import java.util.UUID;

public class RenderFacadeImpl implements RenderFacade {
    private final static Logger LOGGER = LoggerFactory.getLogger(RenderFacadeImpl.class);

    private String tempDirectory;

    public void setTempDirectory(String tempDirectory) {
        this.tempDirectory = tempDirectory;
    }

    private FreemarkerWrapper freemarkerWrapper;

    public void setFreemarkerWrapper(FreemarkerWrapper freemarkerWrapper) {
        this.freemarkerWrapper = freemarkerWrapper;
    }

    private MediaExtractor mediaExtractor;

    public void setMediaExtractor(MediaExtractor mediaExtractor) {
        this.mediaExtractor = mediaExtractor;
    }

    @Override
    public String renderPages(List<DoorwayPage> doorwayPages, GeneralProperties properties, DoorwayContext context) {
        File doorwayDirectory = createDirectory();
        String doorwayPath = doorwayDirectory.getAbsolutePath();
        mediaExtractor.extract(properties.getMedia(), doorwayPath);

        for (DoorwayPage doorwayPage : doorwayPages) {
            LOGGER.debug("rendering page: {}. title = {}", doorwayPage.getFilename(), doorwayPage.getTitle());

            freemarkerWrapper.renderPage(doorwayPage,
                    properties.getTemplates().get(doorwayPage.getPageType()),
                    doorwayPath,
                    properties.getFilesExtension(),
                    properties.getEncoding(),
                    context
            );
        }

        return doorwayPath;
    }

    private File createDirectory() {
        File rootDirectory = new File(tempDirectory);

        if (rootDirectory.exists() && !rootDirectory.isDirectory()) {
            LOGGER.error("{} is not directory", rootDirectory);

            throw new RuntimeException(rootDirectory + " is not directory");
        } else if (!rootDirectory.exists()) {
            LOGGER.debug("creating temporary directory: {}", tempDirectory);

            boolean created = rootDirectory.mkdir();

            if (created) {
                LOGGER.debug("successfully created temp directory: {}", rootDirectory.getAbsolutePath());
            }

        }

        String doorwayRoot = generateUUID();
        File doorwayRootFile = new File(rootDirectory, doorwayRoot);

        if (doorwayRootFile.exists()) {
            LOGGER.error("{} already exists", doorwayRoot);

            throw new RuntimeException(doorwayRoot + " already exists");
        } else {
            boolean created = doorwayRootFile.mkdir();

            if (created) {
                LOGGER.debug("successfully created doorway directory: {}", doorwayRootFile.getAbsolutePath());
            }
        }

        return doorwayRootFile;
    }

    private String generateUUID() {
        UUID uuid = UUID.randomUUID();

        return uuid.toString();
    }
}
