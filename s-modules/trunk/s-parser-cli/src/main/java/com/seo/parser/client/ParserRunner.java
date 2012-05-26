package com.seo.parser.client;

import com.seo.text.link.GoogleNewsProvider;
import com.seo.text.link.GoogleNewsProviderImpl;
import com.seo.text.parse.TextParserFacade;
import com.seo.text.parse.TextParserFacadeImpl;
import com.seo.text.parse.model.ParsePageResponse;
import org.apache.commons.cli.*;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.FileOutputStream;
import java.util.List;

public class ParserRunner {
    private final static Logger LOGGER = LoggerFactory.getLogger(ParserRunner.class);

    public static void main(String[] args) {
        Options options = new Options();
        options.addOption(
                new Option(
                        "f",
                        true,
                        "output filename"
                )
        );
        options.addOption(
                new Option(
                        "k",
                        true,
                        "keyword"
                )
        );
        options.addOption(
                new Option(
                        "c",
                        true,
                        "count"
                )
        );

        CommandLineParser commandLineParser = new BasicParser();
        CommandLine commandLine;
        try {
            commandLine = commandLineParser.parse(options, args);
        } catch (ParseException e) {
            LOGGER.error("parse exception: {} {}", e.getMessage(), e.getStackTrace());

            throw new RuntimeException("parse exception: " + e.getMessage());
        }

        String filename = "output.txt";
        if (commandLine.hasOption("f")) {
            filename = commandLine.getOptionValue("f");
        }

        String keyword;
        if (commandLine.hasOption("k")) {
            keyword = commandLine.getOptionValue("k");
        } else {
            throw new RuntimeException("keyword is required");
        }

        int count = 10;
        if (commandLine.hasOption("c")) {
            count = Integer.valueOf(commandLine.getOptionValue("c"));
        }

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("app-config.xml");
        TextParserFacade textParserFacade = (TextParserFacadeImpl) applicationContext.getBean("textParserFacade");
        GoogleNewsProvider googleNewsProvider = (GoogleNewsProviderImpl) applicationContext.getBean("googleNewsProvider");

        for (int pageNumber = 0; pageNumber < count; pageNumber++) {
            int start = pageNumber * 10;
            List<String> links = googleNewsProvider.fetchNews(keyword, start);

            for (String link : links) {
                try {
                    ParsePageResponse pageResponse = textParserFacade.parsePage(link);

                    IOUtils.write(pageResponse.getContent(), new FileOutputStream(filename, true));
                } catch (Exception e) {
                    LOGGER.error("exception: {} {}", e.getMessage(), e.getStackTrace());
                }
            }
        }
    }
}
