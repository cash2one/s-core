package com.seo.doorgen.render.macros.xml;

import com.seo.doorgen.model.Macros;
import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MacrosRulesParserImpl implements MacrosRulesParser {
    private final static Logger LOGGER = LoggerFactory.getLogger(MacrosRulesParserImpl.class);

    private final static String DIGESTER_CONFIG_PATH = "digester/macros-digester.xml";
    private final static String XML_PATH = "conf/macros.xml";

    @Override
    public List<Macros> parseMacros() {
        LOGGER.debug("parsing macros rules");

        InputStream p = MacrosRulesParserImpl.class.getClassLoader().getResourceAsStream(DIGESTER_CONFIG_PATH);

        InputSource is = new InputSource(p);
        Digester digester = DigesterLoader.createDigester(is);

        try {
            InputStream configInputStream = MacrosRulesParserImpl.class.getClassLoader().getResourceAsStream(XML_PATH);

            return (List<Macros>) digester.parse(configInputStream);
        } catch (IOException e) {
            LOGGER.error("IO error: " + e.getMessage());

            throw new RuntimeException("IO error: " + e.getMessage());
        } catch (SAXException e) {
            LOGGER.error("sax error: " + e.getMessage());

            throw new RuntimeException("sax error: " + e.getMessage());
        }
    }
}
