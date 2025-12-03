package com.ecommerce.productservice;

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

    public Long addPhoto(String title, MultipartFile file) throws IOException {
        Image image = new Image();
        image.setTitle(title);
        image.setImage(
                new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        image = imageRepo.insert(image); return image.getId();
    }

    public Image getPhoto(Long id) {
        return imageRepo.findById(id).get();
    }
}
