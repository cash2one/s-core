package com.seo.compress;

public interface FileDecompressor {
    void decompress(byte[] archive, String destination);
}
