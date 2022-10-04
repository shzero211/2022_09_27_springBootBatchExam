package com.ll.exam.spring_batch_exam.app.order.entity;

import com.ll.exam.spring_batch_exam.app.base.BaseEntity;
import com.ll.exam.spring_batch_exam.app.product.ProductOption;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

import java.time.LocalDateTime;

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

    private String productName;
    private String productOptionColor;
    private String productOptionSize;
    private String productOptionDisplayColor;
    private String productOptionDisplaySize;

    private LocalDateTime orderItemCreateDate;
    public RebateOrderItem(OrderItem orderItem){
        this.orderItem = orderItem;
        order = orderItem.getOrder();
        productOption = orderItem.getProductOption();
        quantity = orderItem.getQuantity();
        price = orderItem.getPrice();
        salePrice = orderItem.getSalePrice();
        wholesalePrice = orderItem.getWholesalePrice();
        pgFee = orderItem.getPgFee();
        payPrice = orderItem.getPayPrice();
        refundPrice = orderItem.getRefundPrice();
        refundQuantity = orderItem.getRefundQuantity();
        isPaid = orderItem.isPaid();

        //상품
        productName=orderItem.getProductOption().getProduct().getName();

        //상품 옵션 추가 데이터
        productOptionColor=orderItem.getProductOption().getColor();
        productOptionSize=orderItem.getProductOption().getSize();
        productOptionDisplayColor=orderItem.getProductOption().getDisplayColor();
        productOptionDisplaySize=orderItem.getProductOption().getDisplaySize();

        orderItemCreateDate=orderItem.getCreateDate();
    }
}
