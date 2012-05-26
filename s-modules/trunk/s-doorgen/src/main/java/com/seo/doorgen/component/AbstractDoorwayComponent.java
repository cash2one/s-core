package com.seo.doorgen.component;

import com.seo.doorgen.keyword.AbstractKeywordHelper;

public abstract class AbstractDoorwayComponent extends AbstractKeywordHelper{
    private final static String EXTENSION_DELIMITER = ".";

    protected String concatenateFilename(String filename, String extension) {
        StringBuffer filenameWithExtension = new StringBuffer();

        filenameWithExtension
                .append(filename)
                .append(EXTENSION_DELIMITER)
                .append(extension);

        return filenameWithExtension.toString();
    }
}
