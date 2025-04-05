package com.ms.productservice.controller;

import com.ms.productservice.dto.ProductRequest;
import com.ms.productservice.dto.ProductResponse;
import com.ms.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponse>> findAll() {
        List<ProductResponse> products = productService.getAllProducts();
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest productRequest) {
        ProductResponse productResponse = productService.addProduct(productRequest);
        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
    }

    @GetMapping("/get/{product_id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("product_id") Long product_id) {
        ProductResponse productResponse = productService.getProductById(product_id);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }


}
