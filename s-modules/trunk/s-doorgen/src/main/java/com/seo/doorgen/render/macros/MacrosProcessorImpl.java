package com.seo.doorgen.render.macros;

import com.seo.doorgen.model.Macros;
import com.seo.doorgen.render.macros.xml.MacrosRulesParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MacrosProcessorImpl implements MacrosProcessor, InitializingBean{
    private final static Logger LOGGER = LoggerFactory.getLogger(MacrosProcessorImpl.class);

    private List<Macros> macroses;

    private MacrosRulesParser macrosRulesParser;

    public void setMacrosRulesParser(MacrosRulesParser macrosRulesParser) {
        this.macrosRulesParser = macrosRulesParser;
    }

    @Override
    public String process(String text) {
        for (Macros macros : macroses) {
            String regexp = macros.getPattern();
            Pattern pattern = Pattern.compile(regexp);

            Matcher matcher = pattern.matcher(text);
            if(matcher.find()) {
                LOGGER.debug("substituting macros: {}", regexp);

                text = text.replaceAll(regexp, macros.getCode());
            }
        }

        return text;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.macroses = macrosRulesParser.parseMacros();
    }
}
