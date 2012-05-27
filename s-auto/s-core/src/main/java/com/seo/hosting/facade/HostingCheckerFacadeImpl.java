package com.seo.hosting.facade;

import com.seo.check.index.service.IndexCheckerService;
import com.seo.check.index.service.yandex.YandexHostingIndexCheckerService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class HostingCheckerFacadeImpl implements HostingCheckerFacade{
    private final static Logger LOGGER = LoggerFactory.getLogger(HostingCheckerFacadeImpl.class);

    private final static String FILENAME = "hostings.txt";

    @Override
    public void check() {
        InputStream inputStream = HostingCheckerFacadeImpl.class.getClassLoader().getResourceAsStream(FILENAME);

        try {
            for (Object line : IOUtils.readLines(inputStream)) {
                String lineString = (String)line;

                String[] freehost = lineString.split(": ");

                IndexCheckerService indexCheckerService = new YandexHostingIndexCheckerService();

                indexCheckerService.checkIndex(freehost[1]);

                Thread.sleep(30000);
            }
        } catch (IOException e) {
            System.out.println("i/o error");
        } catch (InterruptedException e) {
            System.out.println("interrupted");
        }
    }

    public static void main(String[] args) {
        HostingCheckerFacade hostingCheckerFacade = new HostingCheckerFacadeImpl();

        hostingCheckerFacade.check();
    }
}
