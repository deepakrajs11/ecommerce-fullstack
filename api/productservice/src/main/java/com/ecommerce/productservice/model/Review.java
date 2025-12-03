package com.ecommerce.productservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "reviews")
public class Review {
    @Id
    private String id;
    private long userId;
    private long productId;
    private String text;
    private int rating;
    private List<String> imageIds;
}

