package com.comm.image.service;

import java.io.InputStream;
import java.io.OutputStream;

public interface FileService {
    void upload(InputStream inputStream, String filePath);

    void copyTo(String filePath, OutputStream outputStream);

    String getFileUrl(String filePath);

    byte[] getFileAndToArray(String path);

    String getToken();
}
