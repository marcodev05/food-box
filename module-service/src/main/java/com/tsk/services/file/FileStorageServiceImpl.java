package com.tsk.services.file;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageServiceImpl implements  FileStorageService{
    private Path fileStorageLocation ;

    public FileStorageServiceImpl() {
        try {
            this.fileStorageLocation = Paths.get(System.getProperty("user.home") + "/foods/menus")
                    .toAbsolutePath()
                    .normalize();
            Files.createDirectories(fileStorageLocation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            // copy file to the target location (remplace if exist)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            }
            return null;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
