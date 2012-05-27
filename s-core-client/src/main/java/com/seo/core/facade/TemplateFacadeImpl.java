package com.seo.core.facade;

import com.seo.core.dao.TemplateDAO;
import com.seo.core.facade.request.CreateTemplateRequest;
import com.seo.core.model.Template;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

public class TemplateFacadeImpl implements TemplateFacade{
    private final static Logger LOGGER = LoggerFactory.getLogger(TemplateFacadeImpl.class);

    private TemplateDAO templateDAO;

    public void setTemplateDAO(TemplateDAO templateDAO) {
        this.templateDAO = templateDAO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Template> getAll() {
        List<Template> templates = templateDAO.getAll();

        LOGGER.debug("loaded all templates, size: {}", templates.size());

        return templates;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteById(Long id) {
        LOGGER.debug("deleting template, id={}", id);

        Template template = templateDAO.getById(id);
        templateDAO.delete(template);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(Template template) {
        LOGGER.debug("saving temlate, id={}", template.getId());

        templateDAO.save(template);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Template getById(Long id) {
        LOGGER.debug("getting template, id={}", id);

        return templateDAO.getById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void createTemplate(CreateTemplateRequest request) {
        LOGGER.debug("creating new template, name = {}", request.getName());

        String indexTemplateContent;
        String contentTemplateContent;
        String categoryTemplateContent;
        String robotsTemplateContent;
        String sitemapXmlTemplateContent;
        String sitemapTemplateContent;
        try {
            indexTemplateContent = FileUtils.readFileToString(request.getIndexTemplate());
            contentTemplateContent = FileUtils.readFileToString(request.getContentTemplate());
            categoryTemplateContent = FileUtils.readFileToString(request.getCategoryTemplate());
            robotsTemplateContent = FileUtils.readFileToString(request.getRobotsTemplate());
            sitemapXmlTemplateContent = FileUtils.readFileToString(request.getSitemapXmlTemplate());
            sitemapTemplateContent = FileUtils.readFileToString(request.getSitemapTemplate());
        } catch (IOException e) {
            LOGGER.error("cannot read template file: {}", e.getMessage());

            throw new RuntimeException("cannot read template file: " + e.getMessage());
        }

        byte[] mediaBytes;
        try {
            mediaBytes = org.apache.commons.io.FileUtils.readFileToByteArray(request.getMedia());
        } catch (IOException e) {
            LOGGER.error("cannot read media file: {}", e.getMessage());

            throw new RuntimeException("cannot read media file: " + e.getMessage());
        }
        Template template = new Template(
                request.getName(),
                indexTemplateContent,
                contentTemplateContent,
                categoryTemplateContent,
                robotsTemplateContent,
                sitemapTemplateContent,
                sitemapXmlTemplateContent,
                mediaBytes
        );

        this.save(template);
    }
}
