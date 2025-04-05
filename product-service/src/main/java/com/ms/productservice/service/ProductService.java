package com.ms.productservice.service;

import com.ms.productservice.dto.ProductRequest;
import com.ms.productservice.dto.ProductResponse;
import com.ms.productservice.entity.Product;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getAllProducts();

    ProductResponse getProductById(Long productId);

    ProductResponse addProduct(ProductRequest productRequest);
}
