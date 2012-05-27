package com.seo.webclient.util;

import org.apache.commons.codec.binary.Base64;

public class EncoderUtil {
    public static String encodeBase64(byte[] bytes) {
        Base64 encoder = new Base64();

        return new String(encoder.encode(bytes));
    }
}
