package com.seo.image.store;

import com.seo.webclient.model.EncodedImage;

import java.util.List;

public interface ImageStore {
    void saveImage(EncodedImage encodedImage);
    List<EncodedImage> loadImages();
}
