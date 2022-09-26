package com.ll.exam.spring_batch_exam.app.cart;

import com.ll.exam.spring_batch_exam.app.member.Member;
import com.ll.exam.spring_batch_exam.app.product.ProductOption;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartItemRepository cartItemRepository;
    public CartItem addItem(Member member, ProductOption option,int quantity){
        Optional<CartItem> oldCartItem=cartItemRepository.findByMemberIdAndProductOptionId(member.getId(), option.getId());
        if(oldCartItem.isPresent()){
            oldCartItem.get().setQuantity(oldCartItem.get().getQuantity()+quantity);
            cartItemRepository.save(oldCartItem.get());
            return oldCartItem.get();
        }
    CartItem cartItem=CartItem.builder()
            .member(member)
            .productOption(option)
            .quantity(quantity)
            .build();
    return cartItemRepository.save(cartItem);
    }
}
