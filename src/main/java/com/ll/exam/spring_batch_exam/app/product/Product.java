package com.ll.exam.spring_batch_exam.app.product;

import com.ll.exam.spring_batch_exam.app.base.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Product extends BaseEntity {
    private String name;
    private int salePrice;
    private int price;
    private int wholesalePrice;//도매가
    private String makerShopName;
    private boolean isSoldOut;//상품옵션 모두가 soldout 인경우

    @Builder.Default
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ProductOption> productOptions=new ArrayList<>();

    public void addOption(ProductOption option) {
        option.setProduct(this);
        option.setPrice(getPrice());
        option.setWholesalePrice(getWholesalePrice());
        option.setSalePrice(getSalePrice());
        this.productOptions.add(option);
    }
}
