package com.ll.exam.spring_batch_exam.app.base;

import com.ll.exam.spring_batch_exam.app.Member;
import com.ll.exam.spring_batch_exam.app.MemberService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevInitData {
    @Bean
    public CommandLineRunner initData(MemberService memberService){
        return args -> {
                String password="{noop}1234";
                Member member1=memberService.join("user1",password,"user1");
            Member member2=memberService.join("user2",password,"user2");
            Member member3=memberService.join("user3",password,"user3");
            Member member4=memberService.join("user4",password,"user4");
        };
    }
}
