package com.ll.exam.spring_batch_exam.app;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
private final MemberRepository memberRepository;

public Member join(String username,String password,String email){
    Member member=Member.builder()
            .username(username)
            .password(password)
            .email(email)
            .build();
    memberRepository.save(member);
    return member;
}
}
