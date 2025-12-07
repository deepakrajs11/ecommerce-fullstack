package com.ecommerce.productservice.controller;

import com.ecommerce.productservice.dto.ProductResponseDto;
import com.ecommerce.productservice.dto.ReviewRequestDto;
import com.ecommerce.productservice.service.ImageService;
import com.ecommerce.productservice.service.ReviewService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/product/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private ImageService imageService;

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductResponseDto> addReview(
            @RequestPart("review") String reviewJson,
            @RequestPart("image") List<MultipartFile> imageFiles
    ) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            ReviewRequestDto request = mapper.readValue(reviewJson, ReviewRequestDto.class);
            if (request.getImageIds() == null) {
                request.setImageIds(new ArrayList<>());
            }
            for (MultipartFile file : imageFiles) {
                String imageId = imageService.addPhoto("Review"+request.getProductId(), file);
                request.getImageIds().add(imageId);
            }
            return ResponseEntity.ok(reviewService.addReview(request));

        } catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ProductResponseDto(false, e.getMessage()));
        }
    }


}
