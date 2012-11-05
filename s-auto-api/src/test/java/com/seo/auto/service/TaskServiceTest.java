package com.seo.auto.service;

import com.seo.auto.web.model.CreateTaskResponseTO;
import com.seo.auto.web.model.TaskStatusResponseTO;
import com.seo.core.model.AutoConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@ContextConfiguration(value = "classpath:/spring/test-application-auto.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TaskServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskServiceTest.class);

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

        List<CreateTaskResponseTO> responses = taskService.createTask(autoConfig.getId(), 1L);

        for (CreateTaskResponseTO response : responses) {
            validateTask(response);
        }

        autoConfigService.delete(autoConfig.getId());
    }

    @Test
    public void testCreateMultipleTasks() {
        AutoConfig autoConfig = new AutoConfig();

        autoConfig.setName("test autoconfig");
        autoConfig.setConfig(TEST_CONFIG);

        autoConfig = autoConfigService.save(autoConfig);

        assertNotNull(autoConfig);
        assertNotNull(autoConfig.getId());

        List<CreateTaskResponseTO> responses = taskService.createTask(autoConfig.getId(), 1L);

        for (CreateTaskResponseTO response : responses) {
            validateTask(response);
        }

        autoConfigService.delete(autoConfig.getId());
    }

    private void validateTask(CreateTaskResponseTO response) {
        TaskStatusResponseTO status = taskService.getTaskStatus(response.getTaskId());

        while (status.isRunning()) {
            LOGGER.info("thread is still running");

            try {
                Thread.sleep(1000);

                status = taskService.getTaskStatus(response.getTaskId());
            } catch (InterruptedException e) {
                LOGGER.error("interrupted exception: ", e);
            }
        }

        assertEquals("COMPLETED", status.getTaskStatus());
    }
}
