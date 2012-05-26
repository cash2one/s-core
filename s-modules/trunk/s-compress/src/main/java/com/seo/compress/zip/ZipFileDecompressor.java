package com.seo.compress.zip;

import com.seo.compress.FileDecompressor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipFileDecompressor implements FileDecompressor {
    private final static Logger LOGGER = LoggerFactory.getLogger(ZipFileDecompressor.class);

    private final static int ZIP_FILE_BUFFER_SIZE = 4096;

    @Override
    public void decompress(byte[] archive, String destination) {
        LOGGER.debug("decompressing archive to destination: {}", destination);

        //checking destination to be valid
        File destDir = new File(destination);
        if (!destDir.exists()) {
            LOGGER.error("{} not exists", destination);

            throw new RuntimeException("destination dir not exists: " + destination);
        }

        if (!destDir.isDirectory()) {
            LOGGER.error("destination {} is not directory: ", destination);

            throw new RuntimeException("not a directory: " + destination);
        }

        ZipInputStream zipInputStream = new ZipInputStream(
                new ByteArrayInputStream(archive)
        );

        ZipEntry zipEntry;
        try {
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                LOGGER.debug("extracting: {}", zipEntry);

                if (zipEntry.isDirectory()) {
                    boolean dirCreated = new File(destDir, zipEntry.getName()).mkdir();
                } else {

                    BufferedOutputStream outputStream = new BufferedOutputStream(
                            new FileOutputStream(destDir.getPath() + "/" + zipEntry.getName()),
                            ZIP_FILE_BUFFER_SIZE
                    );

                    byte[] buffer = new byte[ZIP_FILE_BUFFER_SIZE];
                    int byteCount;
                    while ((byteCount = zipInputStream.read(buffer, 0, ZIP_FILE_BUFFER_SIZE)) != -1) {
                        outputStream.write(buffer, 0, byteCount);
                    }

                    outputStream.flush();
                    outputStream.close();
                }
            }

            zipInputStream.close();

        } catch (IOException e) {
            LOGGER.error("i/o error: {}", e.getMessage());

            throw new RuntimeException("i/o error: " + e.getMessage());
        }
    }
}
