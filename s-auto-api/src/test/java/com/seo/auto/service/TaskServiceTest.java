package com.seo.auto.service;

import com.seo.auto.AbstractAutoTest;
import com.seo.core.model.AutoConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.junit.Assert.assertNotNull;

@ContextConfiguration(value = "classpath:/spring/test-application-auto.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class TaskServiceTest {
    private static final String TEST_CONFIG = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<project xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"../../src/main/resources/config.xml.xsd\">\n" +
            "    <commands>\n" +
            "        <save-image-command name=\"imagedata\" url=\"http://habrahabr.ru/core/captcha/\"/>\n" +
            "        <load-useragent-command/>\n" +
            "    </commands>\n" +
            "</project>";

    @Inject
    private TaskService taskService;

    @Inject
    private AutoConfigService autoConfigService;

    @Test
    public void testCreateTask() {
        AutoConfig autoConfig = new AutoConfig();

        autoConfig.setName("test autoconfig");
        autoConfig.setConfig(TEST_CONFIG);

        autoConfig = autoConfigService.save(autoConfig);

        assertNotNull(autoConfig);
        assertNotNull(autoConfig.getId());

        taskService.createTask(autoConfig.getId());
    }
}
