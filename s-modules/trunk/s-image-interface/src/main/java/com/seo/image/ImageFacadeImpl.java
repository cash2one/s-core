package com.seo.image;

import com.seo.image.cache.ImageCache;
import com.seo.webclient.model.EncodedImage;

public class ImageFacadeImpl implements ImageFacade{
    private ImageCache imageCache;

    public void setImageCache(ImageCache imageCache) {
        this.imageCache = imageCache;
    }

    @Override
    public EncodedImage getImage(String key) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


}
