package com.ll.exam.spring_batch_exam.app.member;

import com.ll.exam.spring_batch_exam.app.cash.CashLog;
import com.ll.exam.spring_batch_exam.app.cash.CashLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
private final MemberRepository memberRepository;
private final CashLogService cashLogService;
public Member join(String username,String password,String email){
    Member member=Member.builder()
            .username(username)
            .password(password)
            .email(email)
            .build();
    memberRepository.save(member);
    return member;
}
@Transactional
public void addCash(Member member,long changePrice,String eventType){
    CashLog cashLog=cashLogService.addCash(member,changePrice,eventType);
    long newRestCash=member.getRestCash()+cashLog.getChangePrice();
    member.setRestCash(newRestCash);
    memberRepository.save(member);
}
public long getRestCash(Member member){
    return member.getRestCash();
}
}
