package com.ecommerce.productservice.mapper;

import com.ecommerce.productservice.dto.ProductRequestDto;
import com.ecommerce.productservice.model.Product;

public class ProductMapper {
    public Product productRequestProductMapper(ProductRequestDto request, Product product) {
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setSpecifications(request.getSpecifications());
        product.setRating(request.getRating());
        product.setImageIds(request.getImageIds());
        return product;
    }
}
