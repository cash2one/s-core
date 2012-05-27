package com.seo.compress;

/**
 * Compresses files and directories with provided implementation
 */
public interface FileCompressor {
    public void compressDir(String directory);
    public void compressFile(String filename);
}
