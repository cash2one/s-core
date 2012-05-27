package com.seo.image.cache;

import com.seo.image.service.ImageService;
import com.seo.image.store.ImageStore;
import com.seo.webclient.model.EncodedImage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class ImageCacheImpl implements ImageCache {
    private final static Logger LOGGER = LoggerFactory.getLogger(ImageCacheImpl.class);

    private ImageStore imageStore;
    private List<ImageService> imageServices;

    public void setImageServices(List<ImageService> imageServices) {
        this.imageServices = imageServices;
    }

    public void setImageStore(ImageStore imageStore) {
        this.imageStore = imageStore;
    }

    private Map<String, List<EncodedImage>> usedImages = new HashMap<String, List<EncodedImage>>();
    private List<EncodedImage> images;

    @Override
    public EncodedImage getImage(String key) {
        if(images == null) {
            populateImages();
        }

        EncodedImage result = null;

        List<EncodedImage> currentKeyImages = usedImages.get(key);
        for (EncodedImage image : images) {
            if(currentKeyImages == null || !currentKeyImages.contains(image)) {
                LOGGER.debug("found image in cache");

                result = image;

                break;
            }
        }

        if(result == null) {
            result = fetchImage();
        }

        return result;
    }

    private EncodedImage fetchImage() {
        EncodedImage image = getRandomService().fetchImage();

        images.add(image);
        imageStore.saveImage(image);

        return image;
    }

    private void populateImages() {
        images = imageStore.loadImages();
    }

    @Override
    public void putImage(String key, EncodedImage encodedImage) {
        if(usedImages.get(key) == null) {
            usedImages.put(key, new ArrayList<EncodedImage>());
        }

        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("putting image to cache, key=" + key);
        }

        usedImages.get(key).add(encodedImage);
    }

    private ImageService getRandomService() {
        Random random = new Random();

        ImageService imageService = imageServices.get(random.nextInt(
                imageServices.size() - 1
        ));

        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("using service: " + imageService.getName());
        }

        return imageService;
    }
}
