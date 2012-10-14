package com.seo.auto.parser;

import com.seo.auto.model.Project;
import com.seo.auto.parser.exception.ConfigErrorException;
import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.inject.Named;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;

@Named
public class ProjectParserImpl implements ProjectParser{
    private final static Logger LOGGER = LoggerFactory.getLogger(ProjectParserImpl.class);

    private final static String DIGESTER_CONFIG_PATH = "digester/commands-digester.xml";

    @Override
    public Project parseConfig(String config) throws ConfigErrorException{
        InputStream p = ProjectParserImpl.class.getClassLoader().getResourceAsStream(DIGESTER_CONFIG_PATH);

        InputSource is = new InputSource(p);

        Digester digester = DigesterLoader.createDigester(is);

        Reader reader = new StringReader(config);
        try {
            return (Project)digester.parse(reader);
        } catch (IOException e) {
            LOGGER.error("IO error: " + e.getMessage());

            throw new ConfigErrorException("IO error: " + e.getMessage());
        } catch (SAXException e) {
            LOGGER.error("sax error: " + e.getMessage());

            throw new ConfigErrorException("sax error: " + e.getMessage());
        }

    }
}
