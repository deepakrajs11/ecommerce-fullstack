package com.ecommerce.productservice.service;

import com.ecommerce.productservice.dto.ProductResponseDto;
import com.ecommerce.productservice.model.Image;
import com.ecommerce.productservice.repository.ImageRepo;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageService {
    @Autowired
    private ImageRepo imageRepo;

    public String addPhoto(String title, MultipartFile file) throws IOException {
        Image image = new Image();
        image.setTitle(title);
        image.setImage(
                new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        image = imageRepo.insert(image); return image.getId();
    }

    public Image getPhoto(String id) {
        return imageRepo.findById(id).get();
    }

    public ProductResponseDto deletePhoto(String imageId) throws RuntimeException{
        try {
            Image image = imageRepo.findById(imageId).get();
            if(image == null) return new ProductResponseDto(false, "Cannot find the image");
            imageRepo.delete(image);
            return new ProductResponseDto(true, "Image deleted Successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Image updatePhoto(String imageId, MultipartFile file) throws IOException{
        try{
            Image image = imageRepo.findById(imageId).get();
            image.setImage(
                    new Binary(BsonBinarySubType.BINARY, file.getBytes()));
            image = imageRepo.save(image); return image;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}