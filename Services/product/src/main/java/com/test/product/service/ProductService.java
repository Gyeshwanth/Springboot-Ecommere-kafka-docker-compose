package com.test.product.service;

import com.test.product.dto.ProductDto;
import com.test.product.dto.ProductPurchaseDto;
import com.test.product.dto.ProductPurchaseResponse;
import com.test.product.dto.ProductResponseDto;
import com.test.product.exception.ProductDetailFoundException;
import com.test.product.exception.ProductPurchaseException;
import com.test.product.model.Product;
import com.test.product.repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;
    public Integer createProduct(@Valid ProductDto productDto) {
        var product =repository.save(mapper.toProduct(productDto));
        return product.getId();
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseDto> productPurchaseDto) {
        List<Integer> productIds = productPurchaseDto.stream().map(ProductPurchaseDto::getProductId).toList();


        List<Product> storedProducts = repository.findAllByIdInOrderById(productIds);
 if(productIds.size()!=storedProducts.size()){
     throw new ProductDetailFoundException("One or More Products does not exits");
 }
List<ProductPurchaseDto> storesRequest =  productPurchaseDto.stream().sorted(Comparator.comparing(ProductPurchaseDto::getProductId)).toList();

        List<ProductPurchaseResponse> purchaseProducts = new ArrayList<>();
   for (int i=0;i<storedProducts.size();i++){
       Product product = storedProducts.get(i);
       ProductPurchaseDto ppd = storesRequest.get(i);
   if(product.getAvailableQty()<ppd.getQuantity()){
       throw  new ProductPurchaseException("Insufficient stock quantity for product with id:: "+ppd.getProductId());
   }
       double newAvailableQuantity = product.getAvailableQty() - ppd.getQuantity();
     product.setAvailableQty(newAvailableQuantity);
     repository.save(product);
     purchaseProducts.add(mapper.toProductPurchaseResponse(product, ppd.getQuantity()));
   }

    return purchaseProducts;
    }

    public ProductResponseDto getProductById(Integer id) {
  return repository.findById(id).map(mapper::toProductResponse)
          .orElseThrow(()-> new ProductDetailFoundException(String.format("Product Not Found:: with given Id %d",id)));
    }

    public List<ProductResponseDto> getAllProducts() {
  return  repository.findAll().stream().map(mapper::toProductResponse).toList();
    }
}
