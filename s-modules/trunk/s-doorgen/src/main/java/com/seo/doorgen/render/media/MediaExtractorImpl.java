package com.seo.doorgen.render.media;

import com.seo.compress.zip.ZipFileDecompressor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MediaExtractorImpl implements MediaExtractor{
    private final static Logger LOGGER = LoggerFactory.getLogger(MediaExtractorImpl.class);

    private ZipFileDecompressor zipFileDecompressor;

    public void setZipFileDecompressor(ZipFileDecompressor zipFileDecompressor) {
        this.zipFileDecompressor = zipFileDecompressor;
    }

    @Override
    public void extract(byte[] media, String path) {
        LOGGER.debug("extracting media to path: {}", path);

        zipFileDecompressor.decompress(media, path);
    }
}
