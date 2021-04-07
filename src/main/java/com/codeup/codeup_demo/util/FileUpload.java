package com.codeup.codeup_demo.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.annotation.PostConstruct;

import com.codeup.codeup_demo.models.Image;
import com.codeup.codeup_demo.models.Post;
import com.codeup.codeup_demo.models.User;
import com.codeup.codeup_demo.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUpload {

    private static ImageRepository imageDao;
    private static String UPLOAD_PATH;

    @Autowired
    private ImageRepository dao0;

    @Value("${file-upload-path}")
    private String uploadPath;
  
    @PostConstruct     
    private void initStaticDao () {
        imageDao = this.dao0;
        UPLOAD_PATH = this.uploadPath;
    }

    public static Image uploadImage(MultipartFile uploadedImage, User user){


        String filename = uploadedImage.getOriginalFilename();
        Path path = Paths.get(UPLOAD_PATH);

        if(Files.notExists(path)){
            try{
                Files.createDirectories(path);
            }catch(IOException e){
                e.printStackTrace();
                return null;
            }
        }
        
        try {
            uploadedImage.transferTo(Paths.get(UPLOAD_PATH + filename));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        Image image = new Image(
            user,
            UPLOAD_PATH + filename
        );
        
        imageDao.save(image);

        return image;
    }

    public static Image uploadImage(MultipartFile uploadedImage, Post post, User user){


        String filename = uploadedImage.getOriginalFilename();
        Path path = Paths.get(UPLOAD_PATH);

        if(Files.notExists(path)){
            try{
                Files.createDirectories(path);
            }catch(IOException e){
                e.printStackTrace();
                return null;
            }
        }
        
        try {
            uploadedImage.transferTo(Paths.get(UPLOAD_PATH + filename));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        Image image = new Image(
            user,
            post,
            UPLOAD_PATH + filename
        );
        
        imageDao.save(image);
        

        return image;
    }

}
