package com.test.product.controller;




import com.test.product.dto.*;
import com.test.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping()
    public ResponseEntity<Integer> createProduct(@RequestBody @Valid ProductDto productDto){

        return ResponseEntity.ok(service.createProduct(productDto));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(@RequestBody @Valid List<ProductPurchaseDto> productPurchaseDtoList){
        return ResponseEntity.ok(service.purchaseProducts(productPurchaseDtoList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(service.getProductById(id));
    }

    @GetMapping()
    public ResponseEntity<List<ProductResponseDto>> getAllProducts(){
        return ResponseEntity.ok(service.getAllProducts());
    }


}
