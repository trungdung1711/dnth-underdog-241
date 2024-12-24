package com.dnth_underdog_241.online_fashion_shopping.service;


import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.FileLocation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;


@Service
public class FileService
{
    @Value("${com.dnth_underdog_241.online_fashion_shopping.upload}")
    private String uploadDir;


    @Value("${com.dnth_underdog_241.online_fashion_shopping.upload.brands}")
    private String uploadBrandDir;


    @Value("${com.dnth_underdog_241.online_fashion_shopping.upload.products}")
    private String uploadProductDir;


    public String uploadFile(MultipartFile file, FileLocation fileLocation) throws IOException
    {
        if (file.isEmpty())
        {
            throw new IllegalArgumentException("File is empty");
        }

        Path uploadPath = Paths.get(uploadDir);

        // Sanitize file name
        String sanitizedFileName = sanitizeFileName(Objects.requireNonNull(file.getOriginalFilename()));
        String fileName = UUID.randomUUID() + "_" + sanitizedFileName;

        Path targetPath;
        if (fileLocation == FileLocation.BRAND)
        {
            Path brandPath = uploadPath.resolve(uploadBrandDir);
            if (!Files.exists(brandPath)) {
                Files.createDirectories(brandPath);
            }
            targetPath = brandPath.resolve(fileName);
        }
        else
        {
            Path productPath = uploadPath.resolve(uploadProductDir);
            if (!Files.exists(productPath))
            {
                Files.createDirectories(productPath);
            }
            targetPath = productPath.resolve(fileName);
        }

        Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

        // Return public path
        return "/public/images/" + (fileLocation == FileLocation.BRAND ? uploadBrandDir : uploadProductDir) + "/" + fileName;
    }

    private String sanitizeFileName(String originalFileName)
    {
        return originalFileName.replaceAll("[^a-zA-Z0-9\\.\\-]", "_");
    }
}