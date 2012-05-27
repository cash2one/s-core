package com.seo.doorgen.model;

import java.util.Map;

public class GeneralProperties {
    private String textGeneratorName;
    private Map<DoorwayPageType, Template> templates;
    private String filesExtension;
    private String url;
    private String encoding;
    private byte[] media;

    public GeneralProperties(String textGeneratorName, Map<DoorwayPageType, Template> templates, String filesExtension, String url, String encoding, byte[] media) {
        this.textGeneratorName = textGeneratorName;
        this.templates = templates;
        this.filesExtension = filesExtension;
        this.url = url;
        this.encoding = encoding;
        this.media = media;
    }

    public String getTextGeneratorName() {
        return textGeneratorName;
    }

    public Map<DoorwayPageType, Template> getTemplates() {
        return templates;
    }

    public String getFilesExtension() {
        return filesExtension;
    }

    public byte[] getMedia() {
        return media;
    }

    public String getUrl() {
        return url;
    }

    public String getEncoding() {
        return encoding;
    }
}
