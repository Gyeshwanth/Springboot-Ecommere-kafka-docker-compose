package com.test.product.service;

import com.test.product.dto.ProductDto;
import com.test.product.dto.ProductPurchaseResponse;
import com.test.product.dto.ProductResponseDto;
import com.test.product.model.Category;
import com.test.product.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toProduct(ProductDto productDto) {
        return Product.builder().id(productDto.getId())
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .availableQty(productDto.getAvailableQty())
                .category(Category.builder().id(productDto.getId()).build())
                .build();
     }

    public ProductResponseDto toProductResponse(Product product) {
        return new ProductResponseDto(product.getId(),
                                      product.getName(),
                product.getDescription(), product.getAvailableQty(), product.getPrice(),product.getCategory().getId(), product.getCategory().getName(), product.getCategory().getDescription()
                                      );
    }
  public ProductPurchaseResponse  toProductPurchaseResponse(Product product,double qty){
        return new ProductPurchaseResponse(product.getId(), product.getName(), product.getDescription(),product.getPrice(), product.getAvailableQty());
    }
}