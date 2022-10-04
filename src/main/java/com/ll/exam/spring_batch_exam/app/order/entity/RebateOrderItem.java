package com.ll.exam.spring_batch_exam.app.order.entity;

import com.ll.exam.spring_batch_exam.app.base.BaseEntity;
import com.ll.exam.spring_batch_exam.app.product.ProductOption;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
public class RebateOrderItem extends BaseEntity {
    @ManyToOne(fetch = LAZY)
    @ToString.Exclude
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Order order;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private ProductOption productOption;
    @OneToOne(fetch = LAZY)
    @ToString.Exclude
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private OrderItem orderItem;

    private int quantity;

    private int price;//권장판매가
    private int salePrice;//실제판매가
    private int wholesalePrice;//도매가

    private int pgFee;//결제대행사 수수료
    private int payPrice;//결제금액
    private int refundPrice;//환불금액
    private int refundQuantity;//환불할 개수
    private boolean isPaid;//결제 여부
    public RebateOrderItem(OrderItem orderItem){
        this.orderItem = orderItem;
        order = orderItem.getOrder();
        productOption = orderItem.getProductOption();
        quantity = orderItem.getQuantity();
        price = orderItem.getPrice();
        salePrice = orderItem.getSalePrice();
        wholesalePrice = orderItem.getWholesalePrice();
        pgFee = orderItem.getPgFee();
        payPrice = orderItem.getPrice();
        refundPrice = orderItem.getRefundPrice();
        refundQuantity = orderItem.getRefundQuantity();
        isPaid = orderItem.isPaid();
    }
    public RebateOrderItem(ProductOption productOption, int quantity){
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

    public void setRefundDone() {
        if(refundQuantity==quantity)return;
        this.refundQuantity=quantity;
        this.refundPrice=payPrice;
    }
}
