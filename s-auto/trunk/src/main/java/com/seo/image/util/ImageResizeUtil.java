package com.seo.image.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageResizeUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(ImageResizeUtil.class);

    public static String createResizedCopy(String dir, String filename) {
        String thumbFilename = "thumb" + filename;
/*        try {
            File thumbFile = new File(dir + thumbFilename);
            FileOutputStream fileOutputStream = new FileOutputStream(thumbFile);
            Image image = ImageIO.read(new File(dir + filename));
            BufferedImage thumbImage = new BufferedImage(100,
                    100, BufferedImage.TYPE_INT_RGB);

            Graphics2D graphics2D = thumbImage.createGraphics();
            graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            graphics2D.drawImage(image, 0, 0, 100, 100, null);

            // Write the scaled image to the outputstream
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fileOutputStream);
            JPEGEncodeParam param = encoder.
                    getDefaultJPEGEncodeParam(thumbImage);
            int quality = 100; // Use between 1 and 100, with 100 being highest quality
            quality = Math.max(0, Math.min(quality, 100));
            param.setQuality((float) quality / 100.0f, false);
            encoder.setJPEGEncodeParam(param);
            encoder.encode(thumbImage);*/
//            ImageIO.write(thumbImage, "jpg" , fileOutputStream);


/*        } catch (IOException e) {
            LOGGER.error("Image file not found: " + e);
        }*/

        return thumbFilename;
    }
}
