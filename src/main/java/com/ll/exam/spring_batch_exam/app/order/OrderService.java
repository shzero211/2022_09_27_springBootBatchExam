package com.ll.exam.spring_batch_exam.app.order;

import com.ll.exam.spring_batch_exam.app.cart.CartItem;
import com.ll.exam.spring_batch_exam.app.cart.CartService;
import com.ll.exam.spring_batch_exam.app.member.Member;
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
}
