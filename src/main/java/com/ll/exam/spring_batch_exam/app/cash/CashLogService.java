package com.ll.exam.spring_batch_exam.app.cash;

import com.ll.exam.spring_batch_exam.app.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashLogService {
    private final CashLogRepository cashLogRepository;
public CashLog addCash(Member member ,long changePrice,String eventType){
  CashLog cashLog=CashLog.builder()
          .member(member)
          .changePrice(changePrice)
          .eventType(eventType)
          .build();
  return cashLogRepository.save(cashLog);
}
}
