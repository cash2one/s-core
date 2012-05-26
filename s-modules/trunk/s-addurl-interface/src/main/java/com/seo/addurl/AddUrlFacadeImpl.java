package com.seo.addurl;

import com.seo.addurl.service.AddUrlService;
import com.seo.addurl.service.exception.AddUrlFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

public class AddUrlFacadeImpl implements AddUrlFacade{
    private final static Logger LOGGER = LoggerFactory.getLogger(AddUrlFacadeImpl.class);

    private Collection<AddUrlService> addUrlServices;

    public void setAddUrlServices(Collection<AddUrlService> addUrlServices) {
        this.addUrlServices = addUrlServices;
    }

    @Override
    public void addUrl(String url) {
        for (AddUrlService addUrlService : addUrlServices) {
            try {
                addUrlService.postUrl(url);
            } catch (AddUrlFailedException e) {
                LOGGER.error("cannot post url: " + e.getMessage());
            }
        }
    }
}
