package com.ecommerce.productservice.controller;

import ch.qos.logback.core.model.Model;
import com.ecommerce.productservice.dto.ProductResponseDto;
import com.ecommerce.productservice.model.Image;
import com.ecommerce.productservice.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/product/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/add")
    public ResponseEntity<ProductResponseDto> addPhoto(@RequestParam("title") String title,
                                                       @RequestParam("image") MultipartFile image, Model model)
            throws IOException {
        String id = imageService.addPhoto(title, image);
        if(id!=null) return ResponseEntity.
                status(HttpStatus.OK).
                body(new ProductResponseDto<>(true, "Image added successfully/nImage id: "+id));
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ProductResponseDto<>(false, "Cannot able to add image"));
    }

    @GetMapping("/get")
    public ResponseEntity<Image> getPhoto(@RequestParam("id") String imageId){
        Image image = imageService.getPhoto(imageId);
        if (image!=null) return ResponseEntity.status(HttpStatus.OK).body(image);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ProductResponseDto> deletePhoto(@RequestParam("id") String imageId){
        try {
            ProductResponseDto response = imageService.deletePhoto(imageId);
            return response.isSuccess() ? ResponseEntity.status(HttpStatus.OK).body(response) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ProductResponseDto(false, e));
        }
    }
}
