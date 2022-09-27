package com.ll.exam.spring_batch_exam.app.order;

import com.ll.exam.spring_batch_exam.app.cart.CartItem;
import com.ll.exam.spring_batch_exam.app.cart.CartService;
import com.ll.exam.spring_batch_exam.app.member.Member;
import com.ll.exam.spring_batch_exam.app.member.MemberService;
import com.ll.exam.spring_batch_exam.app.product.ProductOption;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly=true)
public class OrderService {
    private final CartService cartService;
    private final OrderRepository orderRepository;
    private final MemberService memberService;
    @Transactional
    public Order createFormCart(Member member) {
        List<CartItem> cartItems=cartService.getItemsByMember(member);
        List<OrderItem> orderItems=new ArrayList<>();
        for(CartItem cartItem:cartItems){
            ProductOption productOption=cartItem.getProductOption();
            if(productOption.isOrderable(cartItem.getQuantity())){
            orderItems.add(new OrderItem(productOption,cartItem.getQuantity()));
            }
            cartService.deleteItem(cartItem);
        }
        return create(member,orderItems);
    }
    @Transactional
    private Order create(Member member, List<OrderItem> orderItems) {
        Order order=Order.builder()
                .member(member)
                .build();
        for(OrderItem orderItem:orderItems){
            order.addOrderItem(orderItem);
        }
        orderRepository.save(order);
        return order;
    }
    @Transactional
    public void payByRestCashOnly(Order order) {
        Member orderer=order.getMember();
        long restCash=orderer.getRestCash();
        int payPrice=order.calculatePayPrice();
        if(payPrice>restCash){
            throw new RuntimeException("예치금이 부족합니다.");
        }
        memberService.addCash(orderer,payPrice*-1,"주문결제__예치금결제");
        order.setPaymentDone();
    }
}
