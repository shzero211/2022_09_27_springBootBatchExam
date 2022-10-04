package com.ll.exam.spring_batch_exam.app.order.repository;

import com.ll.exam.spring_batch_exam.app.order.entity.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
    Page<OrderItem> findAllByIdLessThan(Long id, Pageable pageable);
    Page<OrderItem> findAllByIdBetween(Long fromId,Long toId,Pageable pageable);
}
