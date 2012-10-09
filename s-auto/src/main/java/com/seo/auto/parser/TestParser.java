package com.seo.auto.parser;

import com.seo.auto.model.Project;
import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

public class TestParser {
    public static void main(String[] args) throws IOException, SAXException {
        InputStream p = TestParser.class.getResourceAsStream("/digester/commands-digester.xml");
        InputSource is = new InputSource(p);
        Digester d = DigesterLoader.createDigester(is);

        InputStream data = TestParser.class.getResourceAsStream("/sample/commands.xml");
        Project project = (Project) d.parse(data);
    }
}
