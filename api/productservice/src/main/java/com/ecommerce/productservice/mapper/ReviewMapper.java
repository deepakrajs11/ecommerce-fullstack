package com.ecommerce.productservice.mapper;

import com.ecommerce.productservice.dto.ReviewRequestDto;
import com.ecommerce.productservice.model.Review;

public class ReviewMapper {
    public Review reviewRequestReviewMapper(ReviewRequestDto request, Review review) {
        review.setText(request.getText());
        review.setRating(request.getRating());
        review.setUserId(request.getUserId());
        review.setImageIds(request.getImageIds());
        review.setProductId(request.getProductId());
        return review;
    }
}
