package com.ms.productservice.mapper;

import com.ms.productservice.dto.ProductRequest;
import com.ms.productservice.dto.ProductResponse;
import com.ms.productservice.entity.Product;

public class ProductMapper {

    public static ProductResponse productToProductResponse(Product product) {

        return new ProductResponse(product.getId(), product.getName(), product.getBrand(),
                product.getDescription(), product.getPrice());
    }

    public static Product productRequestToProduct(ProductRequest productRequest) {
        Product product = new Product();

        product.setName(productRequest.name());
        product.setBrand(productRequest.brand());
        product.setDescription(productRequest.description());
        product.setPrice(productRequest.price());

        return product;
    }
}
