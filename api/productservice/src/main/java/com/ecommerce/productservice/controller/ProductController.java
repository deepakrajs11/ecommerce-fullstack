package com.ecommerce.productservice.controller;

import com.ecommerce.productservice.dto.ProductRequestDto;
import com.ecommerce.productservice.dto.ProductResponseDto;
import com.ecommerce.productservice.service.ImageService;
import com.ecommerce.productservice.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ImageService imageService;

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductResponseDto> addProduct(
            @RequestPart("product") String productJson,
            @RequestPart("image") List<MultipartFile> imageFiles
    ) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            ProductRequestDto request = mapper.readValue(productJson, ProductRequestDto.class);
            if (request.getImageIds() == null) {
                request.setImageIds(new ArrayList<>());
            }
            for (MultipartFile file : imageFiles) {
                String imageId = imageService.addPhoto(request.getName(), file);
                request.getImageIds().add(imageId);
            }
            ProductResponseDto response = productService.addProduct(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ProductResponseDto(false, e.getMessage()));
        }
    }



}
