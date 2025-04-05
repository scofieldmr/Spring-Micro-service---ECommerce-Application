package com.ms.productservice.service;


import com.ms.productservice.Repository.ProductRepository;
import com.ms.productservice.dto.ProductRequest;
import com.ms.productservice.dto.ProductResponse;
import com.ms.productservice.entity.Product;
import com.ms.productservice.exception.ProductAlreadyExistsException;
import com.ms.productservice.exception.ProductNotFoundException;
import com.ms.productservice.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductResponse> productResponses = products.stream()
                .map(product -> ProductMapper.productToProductResponse(product)).toList();

        return productResponses;
    }

    @Override
    public ProductResponse getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new ProductNotFoundException("Product Not Found with the ID :" + productId));
        return ProductMapper.productToProductResponse(product);
    }

    @Override
    public ProductResponse addProduct(ProductRequest productRequest) {
        if(productRepository.existsByName(productRequest.name())
                      && productRepository.existsByBrand(productRequest.brand())){
            throw new ProductAlreadyExistsException("Product already Exists with the Name :"+ productRequest.name() +
                            " and Brand : "+productRequest.brand());
        }
        Product product = ProductMapper.productRequestToProduct(productRequest);
        productRepository.save(product);
        return ProductMapper.productToProductResponse(product);
    }


}
