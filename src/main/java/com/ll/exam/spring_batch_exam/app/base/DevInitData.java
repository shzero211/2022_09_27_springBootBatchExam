package com.ll.exam.spring_batch_exam.app.base;

import com.ll.exam.spring_batch_exam.app.member.Member;
import com.ll.exam.spring_batch_exam.app.member.MemberService;
import com.ll.exam.spring_batch_exam.app.product.ProductOption;
import com.ll.exam.spring_batch_exam.app.product.Product;
import com.ll.exam.spring_batch_exam.app.product.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("dev")
public class DevInitData {
    @Bean
    public CommandLineRunner initData(MemberService memberService, ProductService productService){
        return args -> {
                String password="{noop}1234";
                Member member1=memberService.join("user1",password,"user1");
            Member member2=memberService.join("user2",password,"user2");
            Member member3=memberService.join("user3",password,"user3");
            Member member4=memberService.join("user4",password,"user4");

            Product product1=productService.create("단가라",3000,"청평화",Arrays.asList(new ProductOption("RED","44",0),new ProductOption("RED","55",0),new ProductOption("BLUE","44",0),new ProductOption("BLUE","55",0)));
            Product product2=productService.create("쉬폰",3000,"청평화",Arrays.asList(new ProductOption("BLACK","55",0),new ProductOption("WHITE","55",0),new ProductOption("BLACK","44",0),new ProductOption("WHITE","44",0)));
        };
    }
}
