package com.seo.core.facade;

import com.seo.core.dao.TextSourceDAO;
import com.seo.core.facade.request.CreateTextSourceRequest;
import com.seo.core.model.TextSource;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

public class TextSourceFacadeImpl implements TextSourceFacade{
    private final static Logger LOGGER = LoggerFactory.getLogger(TextSourceFacadeImpl.class);

    private TextSourceDAO textSourceDAO;

    public void setTextSourceDAO(TextSourceDAO textSourceDAO) {
        this.textSourceDAO = textSourceDAO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public TextSource getById(Long id) {
        LOGGER.debug("loading text source by id: {}", id);

        return textSourceDAO.getById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<TextSource> getAll() {
        LOGGER.debug("loading all text sources");

        return textSourceDAO.getAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(TextSource textSource) {
        LOGGER.debug("saving text source, id = {}", textSource.getId());

        textSourceDAO.save(textSource);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteById(Long id) {
        LOGGER.debug("deleting text source by id = {}", id);

        TextSource textSource = textSourceDAO.getById(id);
        textSourceDAO.delete(textSource);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void create(CreateTextSourceRequest request) {
        LOGGER.debug("creating new text source, name: {}", request.getName());

        String content;
        try {
            content = FileUtils.readFileToString(request.getContent());
        } catch (IOException e) {
            LOGGER.error("i/o error: " + e.getMessage());

            throw new RuntimeException("i/o error: " + e.getMessage());
        }

        TextSource textSource = new TextSource(request.getName(), content);
        this.save(textSource);
    }

}
