package com.ll.exam.spring_batch_exam.app.cart;

import com.ll.exam.spring_batch_exam.app.member.Member;
import com.ll.exam.spring_batch_exam.app.product.ProductOption;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartItemRepository cartItemRepository;
    public void addItem(Member member, ProductOption option,int quantity){
    CartItem cartItem=CartItem.builder()
            .member(member)
            .productOption(option)
            .quantity(quantity)
            .build();
    cartItemRepository.save(cartItem);
    }
}
