package com.seo.check.index;

import com.seo.check.index.model.IndexCheckResponse;
import com.seo.check.index.service.IndexCheckerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

public class IndexCheckerFacadeImpl implements IndexCheckerFacade {
    private final static Logger LOGGER = LoggerFactory.getLogger(IndexCheckerFacadeImpl.class);

    private Collection<IndexCheckerService> indexCheckerServices;
    private final static int MAX_ATTEMPT_COUNT = 5;

    public void setIndexCheckerServices(Collection<IndexCheckerService> indexCheckerServices) {
        this.indexCheckerServices = indexCheckerServices;
    }

    @Override
    public IndexCheckResponse checkIndex(String url) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("checking indexes for url: " + url);
        }

        IndexCheckResponse indexCheckResponse = new IndexCheckResponse();
        for (IndexCheckerService indexCheckerService : indexCheckerServices) {
            int attemptCount = 0;
            while (attemptCount < MAX_ATTEMPT_COUNT) {
                try {

                    indexCheckResponse.addResult(
                            indexCheckerService.getName(),
                            indexCheckerService.checkIndex(url)
                    );

                    break;

                } catch (Exception e) {
                    LOGGER.error("exception: {} {}", e.getMessage(), e.getStackTrace());
                    attemptCount++;
                }
            }
        }

        return indexCheckResponse;
    }
}
