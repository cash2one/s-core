package com.seo.auto.service;

import com.seo.auto.AbstractAutoTest;
import com.seo.core.model.AutoConfig;
import com.seo.core.service.AutoConfigManager;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.inject.Inject;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class AutoConfigServiceTest extends AbstractAutoTest {

    public static final String TEST_CONFIG_NAME = "testconfig";

    @Inject
    private AutoConfigService autoConfigService;

    @Inject
    private AutoConfigManager autoConfigManager;

    private static final String TEST_CONFIG = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<project xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"../../src/main/resources/config.xml.xsd\">\n" +
            "    <commands>\n" +
            "        <save-image-command name=\"imagedata\" url=\"http://habrahabr.ru/core/captcha/\"/>\n" +
            "        <anti-captcha-command name=\"captcha_response\" image=\"${imagedata}\" ext=\"png\" phrase=\"false\"/>\n" +
            "        <load-useragent-command/>\n" +
            "    </commands>\n" +
            "</project>";

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testSaveAutoConfig() {
        AutoConfig autoConfig = new AutoConfig();

        autoConfig.setName(TEST_CONFIG_NAME);
        autoConfig.setConfig(TEST_CONFIG);

        autoConfig = autoConfigService.save(autoConfig);

        assertNotNull(autoConfig);
        assertNotNull(autoConfig.getId());

        AutoConfig loaded = autoConfigManager.findById(autoConfig.getId());

        assertEquals(TEST_CONFIG_NAME, loaded.getName());
        assertEquals(TEST_CONFIG, loaded.getConfig());
    }

    @Test
    public void testSaveAutoConfigInvalidConfig() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid config format!");

        AutoConfig autoConfig = new AutoConfig();

        autoConfig.setName(TEST_CONFIG_NAME);
        autoConfig.setConfig("invalid config");

        autoConfig = autoConfigService.save(autoConfig);
    }
}
