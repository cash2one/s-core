package com.seo.antigate.connector;

import com.seo.antigate.connector.exception.AntigateUnavailableException;

public interface AntigateConnector {
    String postImage(String imageBase64, String imageExtension, Boolean phrase) throws AntigateUnavailableException;
    void reportInvalid(String imageId) throws AntigateUnavailableException;
    String checkResult(String imageId) throws AntigateUnavailableException;
}
