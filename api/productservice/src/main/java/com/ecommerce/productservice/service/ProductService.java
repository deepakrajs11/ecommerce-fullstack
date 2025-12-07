package com.ecommerce.productservice.service;

import com.ecommerce.productservice.dto.ProductRequestDto;
import com.ecommerce.productservice.dto.ProductResponseDto;
import com.ecommerce.productservice.mapper.ProductMapper;
import com.ecommerce.productservice.model.Product;
import com.ecommerce.productservice.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;
    private ProductMapper productMapper = new ProductMapper();
    public ProductResponseDto addProduct(ProductRequestDto request) {
        try{
            Product product =  productRepo.insert(productMapper.productRequestProductMapper(request, new Product()));
            return new ProductResponseDto(true, "Product Added Successfully!!\nProduct Id: " + product.getId());
        } catch (Exception e) {
            return new ProductResponseDto<>(false, e);
        }

    }
}
