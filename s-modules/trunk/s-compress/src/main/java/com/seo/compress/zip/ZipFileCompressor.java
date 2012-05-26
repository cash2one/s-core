package com.seo.compress.zip;

import com.seo.compress.FileCompressor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFileCompressor implements FileCompressor {
    private final static Logger logger = LoggerFactory.getLogger(ZipFileCompressor.class);
    public final static String EXTENSION = ".zip";

    public void compressDir(String directory) {
        File dir = new File(directory);
        File parentDirectory = dir.getParentFile();

        String filename = dir.getName() + EXTENSION;
        ZipOutputStream zipOutputStream;
        try {
            zipOutputStream = new ZipOutputStream(new FileOutputStream(new File(parentDirectory, filename)));
        } catch (FileNotFoundException e) {
            logger.error("not a regular file: " + filename);
            throw new IllegalStateException("error saving archive");
        }
        zipOutputStream.setLevel(Deflater.BEST_COMPRESSION);
        compressDir(directory, zipOutputStream);
        try {
            zipOutputStream.close();
        } catch (IOException e) {
            logger.error("io exception: " + e);
            throw new IllegalStateException("error writing archive");
        }
    }

    @Override
    public void compressFile(String filename) {
        ZipOutputStream zipOutputStream;
        FileInputStream fis;
        byte[] readBuffer = new byte[2158];
        int bytesIn = 0;

        File file = new File(filename);
        String archiveFileName = filename + EXTENSION;
        try {
            zipOutputStream = new ZipOutputStream(new FileOutputStream(archiveFileName));
        } catch (FileNotFoundException e) {
            logger.error("not a regular file: " + archiveFileName);
            throw new IllegalStateException("error saving archive");
        }

        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            logger.error("not a regular file: " + archiveFileName);
            throw new IllegalStateException("error saving archive");
        }

        ZipEntry anEntry = new ZipEntry(file.getPath());
        //place the zip entry in the ZipOutputStream object
        try {
            zipOutputStream.putNextEntry(anEntry);
            //now write the content of the file to the ZipOutputStream
            while ((bytesIn = fis.read(readBuffer)) != -1) {
                zipOutputStream.write(readBuffer, 0, bytesIn);
            }
            //close the Stream
            fis.close();
            zipOutputStream.close();

        } catch (IOException e) {
            logger.error("error writing archive");
            throw new IllegalStateException("error writing archive");
        }
    }

    //todo: fix recursion
    private void compressDir(String directory, ZipOutputStream zipOutputStream) {
        try {
            //create a new File object based on the directory we have to zip
            File zipDir = new File(directory);
            //get a listing of the directory content
            String[] dirList = zipDir.list();
            byte[] readBuffer = new byte[2158];
            int bytesIn = 0;
            //loop through dirList, and zip the files
            for (String aDirList : dirList) {
                File f = new File(zipDir, aDirList);
                if (f.isDirectory()) {
                    //if the File object is a directory, call this
                    //function again to add its content recursively
                    String filePath = f.getPath();
                    
                    compressDir(filePath, zipOutputStream);
                    //loop again
                    continue;
                }
                //if we reached here, the File object f was not a directory
                //create a FileInputStream on top of f
                FileInputStream fis = new FileInputStream(f);
                ZipEntry anEntry = new ZipEntry(f.getName());
                //place the zip entry in the ZipOutputStream object
                zipOutputStream.putNextEntry(anEntry);
                //now write the content of the file to the ZipOutputStream
                while ((bytesIn = fis.read(readBuffer)) != -1) {
                    zipOutputStream.write(readBuffer, 0, bytesIn);
                }
                //close the Stream
                fis.close();
                zipOutputStream.closeEntry();
            }
        }
        catch (Exception e) {
            //handle exception
        }

    }
}
