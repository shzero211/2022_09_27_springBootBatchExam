package com.ll.exam.spring_batch_exam.app.order;

import com.ll.exam.spring_batch_exam.app.base.BaseEntity;
import com.ll.exam.spring_batch_exam.app.product.ProductOption;
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
public class OrderItem extends BaseEntity {
    @ManyToOne
    @ToString.Exclude
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProductOption productOption;

    private int quantity;

    public OrderItem(ProductOption productOption,int quantity){
        this.productOption=productOption;
        this.quantity=quantity;
    }

}
