package com.seo.auto.facade;

import com.seo.BaseCoreTest;
import org.junit.Ignore;
import org.junit.Test;

import javax.inject.Inject;

public class ConfigFacadeTest extends BaseCoreTest {

    @Inject
    private ConfigFacade configFacade;

    private static final String TEST_CONFIG = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<project xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"../../src/main/resources/config.xml.xsd\">\n" +
            "    <commands>\n" +
            "        <save-image-command name=\"imagedata\" url=\"http://habrahabr.ru/core/captcha/\"/>\n" +
            "        <anti-captcha-command name=\"captcha_response\" image=\"${imagedata}\" ext=\"png\" phrase=\"false\"/>\n" +
            "        <load-useragent-command/>\n" +
            "    </commands>\n" +
            "</project>";

    @Test
    @Ignore
    public void testRunConfig() {
        configFacade.processConfig(TEST_CONFIG);
    }
}
