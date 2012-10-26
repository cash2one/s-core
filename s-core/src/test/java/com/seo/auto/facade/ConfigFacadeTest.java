package com.seo.auto.facade;

import com.seo.AbstractCoreTest;
import com.seo.auto.model.Project;
import org.junit.Ignore;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ConfigFacadeTest extends AbstractCoreTest {

    public static final String TEST_CONFIG_INVALID = "invalid config";
    @Inject
    private ConfigFacade configFacade;

    private static final String TEST_CONFIG = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<project xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"../../src/main/resources/config.xml.xsd\">\n" +
            "    <commands>\n" +
            "        <save-image-command name=\"imagedata\" url=\"http://habrahabr.ru/core/captcha/\"/>\n" +
//            "        <anti-captcha-command name=\"captcha_response\" image=\"${imagedata}\" ext=\"png\" phrase=\"false\"/>\n" +
            "        <load-useragent-command/>\n" +
            "    </commands>\n" +
            "</project>";

    @Test
    public void testRunConfig() {
        Project project = configFacade.processConfig(TEST_CONFIG);

        assertNotNull(project);
    }

    @Test
    public void testValidateConfig() {
        boolean isValidConfig = configFacade.validateConfig(TEST_CONFIG);

        assertTrue(isValidConfig);
    }

    @Test
    public void testValidateConfigNegative() {
        boolean isValidConfig = configFacade.validateConfig(TEST_CONFIG_INVALID);

        assertFalse(isValidConfig);
    }
}
