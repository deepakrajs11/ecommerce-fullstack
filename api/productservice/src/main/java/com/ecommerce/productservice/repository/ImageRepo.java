package com.ecommerce.productservice.repository;

import com.ecommerce.productservice.model.Image;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepo extends MongoRepository<Image, String> {
}