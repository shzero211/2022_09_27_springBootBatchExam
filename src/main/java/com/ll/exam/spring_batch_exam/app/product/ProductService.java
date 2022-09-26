package com.ll.exam.spring_batch_exam.app.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final  ProductRepository productRepository;
    public Product create(String name, int price, String makerShopName , List<ProductOption> options){

    Product product=Product.builder()
            .name(name)
            .price(price)
            .makerShopName(makerShopName)
            .build();
    for(ProductOption productOption:options){
        product.addOption(productOption);
    }
    return productRepository.save(product);
    }


}
