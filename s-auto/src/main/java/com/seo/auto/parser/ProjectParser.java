package com.seo.auto.parser;

import com.seo.auto.model.Project;
import com.seo.auto.parser.exception.ConfigErrorException;

public interface ProjectParser {
    Project parseConfig(String config) throws ConfigErrorException;
}
