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

    private int price;//권장판매가
    private int salePrice;//실제판매가
    private int wholesalePrice;//도매가

    private int pgFee;//결제대행사 수수료
    private int payPrice;//결제금액
    private int refundPrice;//환불금액
    private int refundQuantity;//환불할 개수
    private boolean isPaid;//결제 여부

    public OrderItem(ProductOption productOption,int quantity){
        this.price=productOption.getPrice();
        this.salePrice=productOption.getSalePrice();
        this.wholesalePrice=productOption.getWholesalePrice();
        this.productOption=productOption;
        this.quantity=quantity;
    }

    public int calculatePayPrice() {
        return salePrice*quantity;
    }

    public void setPaymentDone() {
        this.pgFee=0;
        this. payPrice=calculatePayPrice();
        this.isPaid=true;
    }
}
