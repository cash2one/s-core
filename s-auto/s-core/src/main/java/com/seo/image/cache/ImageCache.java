package com.seo.image.cache;

import com.seo.webclient.model.EncodedImage;

public interface ImageCache {
    EncodedImage getImage(String key);
    void putImage(String key, EncodedImage encodedImage);
}
