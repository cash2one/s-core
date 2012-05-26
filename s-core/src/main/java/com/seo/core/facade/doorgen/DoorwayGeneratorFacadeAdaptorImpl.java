package com.seo.core.facade.doorgen;

import com.seo.core.model.DoorwayConfiguration;
import com.seo.core.model.KeywordGroup;
import com.seo.core.model.Template;
import com.seo.core.model.TextSource;
import com.seo.doorgen.facade.DoorwayGeneratorFacade;
import com.seo.doorgen.model.DoorwayPageType;
import com.seo.doorgen.model.GeneralProperties;
import com.seo.doorgen.model.GenerateRequest;
import com.seo.doorgen.model.Keyword;
import com.seo.doorgen.model.component.CategoryPageProperties;
import com.seo.doorgen.model.component.ContentPageProperties;
import com.seo.doorgen.model.component.IndexPageProperties;
import com.seo.message.AbstractMessageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoorwayGeneratorFacadeAdaptorImpl extends AbstractMessageHelper implements DoorwayGeneratorFacadeAdaptor{
    private final static Logger LOGGER = LoggerFactory.getLogger(DoorwayGeneratorFacadeAdaptorImpl.class);

    private DoorwayGeneratorFacade doorwayGeneratorFacade;

    public void setDoorwayGeneratorFacade(DoorwayGeneratorFacade doorwayGeneratorFacade) {
        this.doorwayGeneratorFacade = doorwayGeneratorFacade;
    }

    @Override
    public String createDoorway(DoorwayConfiguration configuration, Template template, KeywordGroup keywordGroup, TextSource textSource, String url) {
        LOGGER.debug("adapting create doorway request to doorway generator facade");

        GenerateRequest request = createGenerateRequest(configuration, template, keywordGroup, textSource, url);
        doorwayGeneratorFacade.setMessageListener(getMessageListener());
        String doorwayPath = doorwayGeneratorFacade.generate(request);

        return doorwayPath;
    }

    private GenerateRequest createGenerateRequest(DoorwayConfiguration configuration, Template template, KeywordGroup keywordGroup, TextSource textSource, String url) {
        LOGGER.debug("creating generate request");

        GeneralProperties generalProperties = new GeneralProperties(
                "clear", //todo: fix
                mapTemplates(template),
                configuration.getGeneralConfiguration().getFileExtension(),
                url,
                configuration.getGeneralConfiguration().getEncoding(),
                template.getMedia()
        );
        IndexPageProperties indexPageProperties = new IndexPageProperties(
                configuration.getIndexPageConfiguration().getTitle()
        );
        ContentPageProperties contentPageProperties = new ContentPageProperties(
                configuration.getContentPageConfiguration().getTitle(),
                configuration.getContentPageConfiguration().getMinNumberOfPages(),
                configuration.getContentPageConfiguration().getMaxNumberOfPages(),
                configuration.getContentPageConfiguration().getMinNumberOfContents(),
                configuration.getContentPageConfiguration().getMaxNumberOfContents(),
                configuration.getContentPageConfiguration().getMinContentLength(),
                configuration.getContentPageConfiguration().getMaxContentLength()
        );
        CategoryPageProperties categoryPageProperties = new CategoryPageProperties(
                configuration.getCategoryPageConfiguration().getMinNumberOfPages(),
                configuration.getCategoryPageConfiguration().getMaxNumberOfPages()
        );

        GenerateRequest request = new GenerateRequest(
                generalProperties,
                indexPageProperties,
                contentPageProperties,
                categoryPageProperties,
                createTextSource(textSource),
                createKeywords(keywordGroup),
                template.getMedia()
        );

        return request;
    }

    private Map<DoorwayPageType, com.seo.doorgen.model.Template> mapTemplates(Template template) {
        Map<DoorwayPageType, com.seo.doorgen.model.Template> templateMap = new HashMap<DoorwayPageType, com.seo.doorgen.model.Template>();

        templateMap.put(DoorwayPageType.INDEX, new com.seo.doorgen.model.Template(DoorwayPageType.INDEX, template.getIndexTemplate()));
        templateMap.put(DoorwayPageType.CONTENT, new com.seo.doorgen.model.Template(DoorwayPageType.CONTENT, template.getContentTemplate()));
        templateMap.put(DoorwayPageType.CATEGORY, new com.seo.doorgen.model.Template(DoorwayPageType.CATEGORY, template.getCategoryTemplate()));
        templateMap.put(DoorwayPageType.ROBOTS, new com.seo.doorgen.model.Template(DoorwayPageType.ROBOTS, template.getRobotsTemplate()));
        templateMap.put(DoorwayPageType.SITEMAP_XML, new com.seo.doorgen.model.Template(DoorwayPageType.SITEMAP_XML, template.getSitemapXmlTemplate()));
        templateMap.put(DoorwayPageType.SITEMAP, new com.seo.doorgen.model.Template(DoorwayPageType.SITEMAP, template.getSitemapTemplate()));

        return templateMap;
    }

    private com.seo.doorgen.model.TextSource createTextSource(TextSource textSource) {
        com.seo.doorgen.model.TextSource source = new com.seo.doorgen.model.TextSource(textSource.getContent());

        return source;
    }

    private List<Keyword> createKeywords(KeywordGroup keywordGroup) {
        List<Keyword> keywords = new ArrayList<Keyword>();

        for (com.seo.core.model.Keyword keyword : keywordGroup.getKeywords()) {
            //hardcoded for one part keywords
            //todo: fix for multipart keywords

            Keyword key = new Keyword(keyword.getKeywordParts().get(0).getContent());
            keywords.add(key);
        }

        return keywords;
    }

}
