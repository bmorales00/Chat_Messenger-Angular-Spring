package com.brianmorales.chatapplication.services;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;


@Service
public class FileStorageService {

    @Value("${profile.picture.storage.location}")
    private String imgUrlPath;


    public String storeProfilePicture(MultipartFile file){
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path targetPath = Path.of(imgUrlPath, fileName);

        try{
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException e){
            System.out.println("Could not save file" + file.getName());
        }
        return fileName;
    }

    public Resource fetchImage(String fileName){
        Path imgPath = Paths.get(this.imgUrlPath, fileName);
        try{
            Resource imgResource = new UrlResource(imgPath.toUri());
            if(imgResource.exists() && imgResource.isReadable()){
                return imgResource;
            }
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        return null;
    }
}
