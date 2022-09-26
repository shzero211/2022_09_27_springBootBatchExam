package com.ll.exam.spring_batch_exam.app.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final  ProductRepository productRepository;
    public Product create(String name,int price,String makerShopName ){

    Product product=Product.builder()
            .name(name)
            .price(price)
            .makerShopName(makerShopName)
            .build();
    return productRepository.save(product);
    }
}
