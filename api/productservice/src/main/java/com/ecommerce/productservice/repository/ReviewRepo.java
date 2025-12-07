package com.ecommerce.productservice.repository;

import com.ecommerce.productservice.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewRepo extends MongoRepository<Review, String> {
}
