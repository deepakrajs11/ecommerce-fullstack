package com.ecommerce.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequestDto {
    private long userId;
    private String productId;
    private String text;
    private int rating;
    private List<String> imageIds;
}
