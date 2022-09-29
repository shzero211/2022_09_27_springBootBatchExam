package com.ll.exam.spring_batch_exam.app.product.entity;

import com.ll.exam.spring_batch_exam.app.base.BaseEntity;
import com.ll.exam.spring_batch_exam.app.product.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class ProductBackup extends BaseEntity {
    private String name;
    private int salePrice;
    private int price;
    private int wholesalePrice;//도매가
    private String makerShopName;
    private boolean isSoldOut;//상품옵션 모두가 soldout 인경우

    @OneToOne(fetch = FetchType.LAZY)
    private Product product;

    public ProductBackup(Product product){
        this.product=product;
        this.salePrice=product.getSalePrice();
        this.price=product.getPrice();
        this.wholesalePrice=product.getWholesalePrice();
        this.name=product.getName();
        this.makerShopName=product.getMakerShopName();
        this.isSoldOut=product.isSoldOut();
    }
}
