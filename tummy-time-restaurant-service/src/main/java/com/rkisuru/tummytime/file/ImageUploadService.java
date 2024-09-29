package com.rkisuru.tummytime.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageUploadService {

    @Value("${file.upload.path}")
    private String uploadPath;

    private static final String[] ALLOWED_EXTENSIONS = {"jpg", "jpeg", "png", "gif"};

    public String uploadImage(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        if (fileName == null || !isValidExtension(fileName)) {
            throw new IllegalArgumentException("Invalid file name");
        }

        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString() + fileExtension;

        Path path = Paths.get(uploadPath, newFileName);
        Files.createDirectories(path.getParent());
        Files.write(path, file.getBytes());

        Path filePath = Paths.get(uploadPath, newFileName);
        return filePath.toString();
    }

    private boolean isValidExtension(String fileName) {
        for (String ext : ALLOWED_EXTENSIONS) {
            if (fileName.toLowerCase().endsWith(ext)) {
                return true;
            }
        } return false;
    }
}
