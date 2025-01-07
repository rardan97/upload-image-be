package com.blackcode.upload_image_be.config;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    String store(MultipartFile file);
    byte[] load(String filename);
    void delete(String filename);
}
