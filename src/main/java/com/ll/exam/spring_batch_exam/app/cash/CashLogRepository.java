package com.ll.exam.spring_batch_exam.app.cash;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CashLogRepository extends JpaRepository<CashLog,Long> {
}
