package com.seo.core.facade;

import au.com.bytecode.opencsv.CSVReader;
import com.seo.core.dao.KeywordGroupDAO;
import com.seo.core.facade.request.CreateKeywordGroupRequest;
import com.seo.core.model.Keyword;
import com.seo.core.model.KeywordGroup;
import com.seo.core.model.KeywordPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KeywordGroupFacadeImpl implements KeywordGroupFacade{
    private final static Logger LOGGER = LoggerFactory.getLogger(KeywordGroupFacadeImpl.class);

    private final static char CSV_SEPARATOR = ';';

    private KeywordGroupDAO keywordGroupDAO;

    public void setKeywordGroupDAO(KeywordGroupDAO keywordGroupDAO) {
        this.keywordGroupDAO = keywordGroupDAO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(KeywordGroup keywordGroup) {
        LOGGER.debug("saving keyword group, id = {}", keywordGroup.getId());

        keywordGroupDAO.save(keywordGroup);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<KeywordGroup> getAll() {
        LOGGER.debug("getting all keyword groups");

        return keywordGroupDAO.getAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void create(CreateKeywordGroupRequest request) {
        LOGGER.debug("creating keyword group, name={}", request.getName());

        CSVReader csvReader;
        try {
            csvReader = new CSVReader(
                    new FileReader(request.getContent()),
                    CSV_SEPARATOR
            );
        } catch (FileNotFoundException e) {
            LOGGER.debug("file not found: {}", e.getMessage());

            throw new RuntimeException("file not found: " + e.getMessage());
        }

        List<Keyword> keywords = new ArrayList<Keyword>();
        String[] nextLine;
        try {
            while((nextLine = csvReader.readNext()) != null) {
                List<KeywordPart> keywordParts = new ArrayList<KeywordPart>();
                for (String keywordPart : nextLine) {
                    LOGGER.debug("processing part: {}", keywordPart);

                    KeywordPart keyPart = new KeywordPart(keywordPart);

                    keywordParts.add(keyPart);
                }

                Keyword keyword = new Keyword(keywordParts);
                keywords.add(keyword);
            }
        } catch (IOException e) {
            LOGGER.error("i/o error: {}", e.getMessage());

            throw new RuntimeException("i/o error: " + e.getMessage());
        }

        KeywordGroup keywordGroup = new KeywordGroup(request.getName(), keywords);

        this.save(keywordGroup);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Long id) {
        LOGGER.debug("deleting keyword group, id={}", id);

        KeywordGroup keywordGroup = keywordGroupDAO.getById(id);
        keywordGroupDAO.delete(keywordGroup);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public KeywordGroup getById(Long id) {
        LOGGER.debug("getting keyword group by id, id = {}", id);

        return keywordGroupDAO.getById(id);
    }
}
