package com.ll.exam.spring_batch_exam.app.order.entity;

import com.ll.exam.spring_batch_exam.app.base.BaseEntity;
import com.ll.exam.spring_batch_exam.app.member.Member;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
@Table(name = "product_order")
public class Order extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Builder.Default
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<OrderItem> orderItems=new ArrayList<>();

    public void addOrderItem(OrderItem orderItem){
        orderItem.setOrder(this);
        orderItems.add(orderItem);
    }

    public int calculatePayPrice() {
        int payPrice=0;
        for(OrderItem orderItem:orderItems){
            payPrice+=orderItem.calculatePayPrice();
        }
        return payPrice;
    }

    public void setPaymentDone() {
        for(OrderItem orderItem:orderItems){
            orderItem.setPaymentDone();
        }
    }

    public void setRefundDone() {
        for(OrderItem orderItem:orderItems){
            orderItem.setRefundDone();
        }
    }

    public int getPayPrice() {
        int payPrice=0;
        for(OrderItem orderItem : orderItems){
            payPrice+=orderItem.getPayPrice();
        }
        return payPrice;
    }
}
