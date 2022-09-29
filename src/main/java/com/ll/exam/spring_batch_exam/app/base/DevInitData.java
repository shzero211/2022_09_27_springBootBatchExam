package com.ll.exam.spring_batch_exam.app.base;

import com.ll.exam.spring_batch_exam.app.cart.CartService;
import com.ll.exam.spring_batch_exam.app.member.Member;
import com.ll.exam.spring_batch_exam.app.member.MemberService;
import com.ll.exam.spring_batch_exam.app.order.Order;
import com.ll.exam.spring_batch_exam.app.order.OrderService;
import com.ll.exam.spring_batch_exam.app.product.Product;
import com.ll.exam.spring_batch_exam.app.product.ProductOption;
import com.ll.exam.spring_batch_exam.app.product.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("dev")
@Slf4j
public class DevInitData {
    @Bean
    public CommandLineRunner initData(MemberService memberService, ProductService productService, CartService cartService, OrderService orderService){
        return args -> {
                String password="{noop}1234";
                Member member1=memberService.join("user1",password,"user1");
            Member member2=memberService.join("user2",password,"user2");
            Member member3=memberService.join("user3",password,"user3");
            Member member4=memberService.join("user4",password,"user4");
            //만원충전
            memberService.addCash(member1,10000,"충전__무통장입금");
            //2만원충전
            memberService.addCash(member1,20000,"충전__무통장입금");
            memberService.addCash(member1,-5000,"출금__일반");
            memberService.addCash(member1,300_000,"충전__무통장입금");
            Product product1=productService.create("단가라",5000,2000,"청평화",Arrays.asList(new ProductOption("RED","44"),new ProductOption("RED","55"),new ProductOption("BLUE","44"),new ProductOption("BLUE","55")));
            Product product2=productService.create("쉬폰",3000,2000,"청평화",Arrays.asList(new ProductOption("BLACK","55"),new ProductOption("WHITE","55"),new ProductOption("BLACK","44"),new ProductOption("WHITE","44")));

            ProductOption productOption__RED_44=product1.getProductOptions().get(0);
            ProductOption productOption__BLUE_44=product1.getProductOptions().get(3);
            cartService.addItem(member1,productOption__RED_44,2);
            cartService.addItem(member1,productOption__RED_44,2);
            cartService.addItem(member1,productOption__BLUE_44,2);

            Order order1=orderService.createFormCart(member1);
            int order1PayPrice=order1.calculatePayPrice();
            orderService.payByRestCashOnly(order1);

            //2번주문 생성
            //-장바구니에 담기
            //-주문 생성성
            ProductOption product2Option__BLACK_44=product2.getProductOptions().get(0);
            ProductOption product2Option__WHITE_44=product2.getProductOptions().get(2);

            cartService.addItem(member2,productOption__RED_44,1);
            cartService.addItem(member2,product2Option__BLACK_44,2);
            cartService.addItem(member2,product2Option__WHITE_44,2);

            Order order2=orderService.createFormCart(member2);
            log.debug("order2 payPrice : "+order2.calculatePayPrice());
            memberService.addCash(member2,17000,"충전__무통장입금");
            orderService.payByRestCashOnly(order2);
        };
    }
}
