package com.ll.exam.spring_batch_exam.app.cart;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
Optional<CartItem> findByMemberIdAndProductOptionId(long memberId,long optionId);
}
