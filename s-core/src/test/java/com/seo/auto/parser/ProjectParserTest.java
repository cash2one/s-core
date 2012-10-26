package com.seo.auto.parser;

import com.seo.AbstractCoreTest;
import com.seo.auto.model.Project;
import com.seo.auto.parser.exception.ConfigErrorException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

import javax.inject.Inject;
import java.io.File;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ProjectParserTest extends AbstractCoreTest {

    @Inject
    private ProjectParser projectParser;

    @Value("classpath:/config/sample.xml")
    private File sampleConfig;

    @Test
    public void testParseConfig() throws Exception {
        String sampleConfigContent = FileUtils.readFileToString(sampleConfig);

        Project project = projectParser.parseConfig(sampleConfigContent);

        assertNotNull(project);
        assertNotNull(project.getCommands());
        assertTrue(project.getCommands().size() > 0);
    }

    @Test(expected = ConfigErrorException.class)
    public void testParseConfigNegativeInvalidConfig() throws Exception {
        projectParser.parseConfig("invalid config");
    }
}
