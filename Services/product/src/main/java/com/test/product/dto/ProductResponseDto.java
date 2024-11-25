package com.test.product.dto;

import com.test.product.model.Category;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ProductResponseDto {

    private Integer id;
    private String name;
    private String description;
    private double availableQty;
    private BigDecimal price;


    private Integer categoryId;
    private String categoryName;
    private String categoryDescription;


}
