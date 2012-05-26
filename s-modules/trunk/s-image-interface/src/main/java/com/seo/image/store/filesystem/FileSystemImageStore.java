package com.seo.image.store.filesystem;

import com.seo.image.store.ImageStore;
import com.seo.webclient.model.EncodedImage;

import java.util.List;

public class FileSystemImageStore implements ImageStore {
    private String storagePath;

    public void setStoragePath(String storagePath) {
        this.storagePath = storagePath;
    }

    @Override
    public void saveImage(EncodedImage encodedImage) {
        //todo: implement
    }

    @Override
    public List<EncodedImage> loadImages() {
        //todo: implement
        return null;
    }
}
