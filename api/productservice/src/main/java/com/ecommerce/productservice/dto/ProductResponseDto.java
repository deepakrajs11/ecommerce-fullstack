package com.ecommerce.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductResponseDto<T> {
    private boolean success;
    private T data;
}
