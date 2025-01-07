package com.blackcode.upload_image_be.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageService implements StorageService{

    @Value("${upload.dir}")
    private String uploadDir;


    @Override
    public String store(MultipartFile file) {
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs(); // Membuat folder jika belum ada
        }

        // Mengambil nama file asli
//        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());

        // Menghasilkan nama file baru dengan UUID agar unik
        String filename = UUID.randomUUID().toString();

        try {
            // Menyimpan file ke folder yang telah ditentukan
            Path targetLocation = Paths.get(uploadDir + File.separator + filename);
            Files.copy(file.getInputStream(), targetLocation);
        } catch (IOException ex) {
            throw new RuntimeException("Failed to store file " + filename, ex);
        }

        return filename; // Mengembalikan nama file yang telah disimpan

    }

    @Override
    public byte[] load(String filename) {
        try {
            Path filePath = Paths.get(uploadDir).resolve(filename).normalize();
            return Files.readAllBytes(filePath);
        } catch (IOException ex) {
            throw new RuntimeException("Failed to load file " + filename, ex);
        }
    }

    @Override
    public void delete(String filename) {
        try {
            Path filePath = Paths.get(uploadDir).resolve(filename).normalize();
            Files.delete(filePath); // Menghapus file dari sistem
        } catch (IOException ex) {
            throw new RuntimeException("Failed to delete file " + filename, ex);
        }
    }
}
