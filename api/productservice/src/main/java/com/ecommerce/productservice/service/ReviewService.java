package com.ecommerce.productservice.service;

import com.ecommerce.productservice.dto.ProductResponseDto;
import com.ecommerce.productservice.dto.ReviewRequestDto;
import com.ecommerce.productservice.mapper.ReviewMapper;
import com.ecommerce.productservice.model.Review;
import com.ecommerce.productservice.repository.ReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepo reviewRepo;
    private final ReviewMapper reviewMapper = new ReviewMapper();
    public ProductResponseDto addReview(ReviewRequestDto request) {
        try {
            Review review = reviewRepo.insert(reviewMapper.reviewRequestReviewMapper(request, new Review()));
            return new ProductResponseDto(true, "Review Added successfully\nReview Id:"+review.getId());
        } catch (Exception e){
            return new ProductResponseDto(false, e);
        }
    }
}
