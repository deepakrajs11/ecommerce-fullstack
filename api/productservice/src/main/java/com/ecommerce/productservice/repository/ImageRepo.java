package com.ecommerce.productservice.repository;

import com.ecommerce.productservice.model.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageRepo extends MongoRepository<Image, Long> {
}
