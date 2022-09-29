package com.ll.exam.spring_batch_exam.app.product;

import com.ll.exam.spring_batch_exam.app.product.entity.Product;
import com.ll.exam.spring_batch_exam.app.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public Product create(String name, int salePrice, int wholesalePrice, String makerShopName , List<ProductOption> options){
    int price=(int)Math.ceil(wholesalePrice*1.6)/100*100;
    Product product=Product.builder()
            .name(name)
            .salePrice(salePrice)
            .price(price)
            .wholesalePrice(wholesalePrice)
            .makerShopName(makerShopName)
            .build();
    for(ProductOption productOption:options){
        product.addOption(productOption);
    }
    return productRepository.save(product);
    }


}
