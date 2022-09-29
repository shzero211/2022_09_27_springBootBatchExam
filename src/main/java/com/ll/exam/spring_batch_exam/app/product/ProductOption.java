package com.ll.exam.spring_batch_exam.app.product;

import com.ll.exam.spring_batch_exam.app.base.BaseEntity;
import com.ll.exam.spring_batch_exam.app.product.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
public class ProductOption extends BaseEntity {
    private String color;
    private String size;
    private String displayColor;
    private String displaySize;
    private int price;
    private int salePrice;
    private int wholesalePrice;
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
    private boolean isSoldOut;//사입처에서의 품절 여부
    private int stockQuantity;//쇼핑몰에서 보유한 물건 개수
    public ProductOption(String  color, String size) {
        this.color=color;
        this.displayColor=color;
        this.size=size;
        this.displaySize=size;

    }

    public boolean isOrderable(int quantity) {
        if(isSoldOut()==false)return true;
        return getStockQuantity()>=quantity;
    }

}
