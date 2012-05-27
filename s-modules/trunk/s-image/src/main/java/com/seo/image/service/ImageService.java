package com.seo.image.service;

import com.seo.webclient.model.EncodedImage;

public interface ImageService {
    EncodedImage fetchImage();
    String getName();
}
