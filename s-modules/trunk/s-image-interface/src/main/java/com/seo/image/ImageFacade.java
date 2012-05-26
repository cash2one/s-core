package com.seo.image;

import com.seo.webclient.model.EncodedImage;

public interface ImageFacade {
    EncodedImage getImage(String key);
}
