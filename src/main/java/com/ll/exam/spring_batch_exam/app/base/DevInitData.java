package com.ll.exam.spring_batch_exam.app.base;

import com.ll.exam.spring_batch_exam.app.cart.CartService;
import com.ll.exam.spring_batch_exam.app.member.Member;
import com.ll.exam.spring_batch_exam.app.member.MemberService;
import com.ll.exam.spring_batch_exam.app.order.entity.Order;
import com.ll.exam.spring_batch_exam.app.order.OrderService;
import com.ll.exam.spring_batch_exam.app.product.entity.Product;
import com.ll.exam.spring_batch_exam.app.product.ProductOption;
import com.ll.exam.spring_batch_exam.app.product.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;
import java.util.List;

@Configuration
@Profile("dev")
@Slf4j
public class DevInitData {
    private boolean initDataDone=false;
    @Bean
    public CommandLineRunner initData(MemberService memberService, ProductService productService, CartService cartService, OrderService orderService){
        return args -> {
            if(initDataDone) return;
            initDataDone=true;

            class Helper{
                public Order order(Member member, List<ProductOption> productOptions){
                    for(int i=0;i<productOptions.size();i++){
                        ProductOption productOption=productOptions.get(i);
                        cartService.addItem(member,productOption,i+1);
                    }
                    return orderService.createFromCart(member);
                }
            }
            Helper helper=new Helper();

                String password="{noop}1234";
                Member member1=memberService.join("user1",password,"user1");
            Member member2=memberService.join("user2",password,"user2");
            Member member3=memberService.join("user3",password,"user3");
            Member member4=memberService.join("user4",password,"user4");
            //만원충전
            memberService.addCash(member1,1_000_000,"충전__무통장입금");
            //2만원충전
            memberService.addCash(member1,20000,"충전__무통장입금");
            memberService.addCash(member1,-5000,"출금__일반");
            memberService.addCash(member1,300_000,"충전__무통장입금");
            Product product1=productService.create("단가라",5000,2000,"청평화",Arrays.asList(new ProductOption("RED","44"),new ProductOption("RED","55"),new ProductOption("BLUE","44"),new ProductOption("BLUE","55")));
            Product product2=productService.create("쉬폰",3000,2000,"청평화",Arrays.asList(new ProductOption("BLACK","55"),new ProductOption("WHITE","55"),new ProductOption("BLACK","44"),new ProductOption("WHITE","44")));

            ProductOption product1Option__RED_44=product1.getProductOptions().get(0);
            ProductOption product1Option__RED_55=product1.getProductOptions().get(1);
            ProductOption product1Option__BLUE_44=product1.getProductOptions().get(2);
            ProductOption product1Option__BLUE_55=product1.getProductOptions().get(3);
            Order order1=helper.order(member1,Arrays.asList(product1Option__RED_44,product1Option__RED_44,product1Option__RED_44,product1Option__BLUE_44));

            int order1PayPrice=order1.calculatePayPrice();
            orderService.payByRestCashOnly(order1);

            //2번주문 생성
            //-장바구니에 담기
            //-주문 생성성
            ProductOption product2Option__BLACK_44=product2.getProductOptions().get(0);
            ProductOption product2Option__BLACK_55=product2.getProductOptions().get(1);
            ProductOption product2Option__WHITE_44=product2.getProductOptions().get(2);
            ProductOption product2Option__WHITE_55=product2.getProductOptions().get(3);
            Order order2=helper.order(member2, Arrays.asList(product1Option__RED_44,product2Option__BLACK_44,product2Option__WHITE_44));
            log.debug("order2 payPrice : "+order2.calculatePayPrice());
            memberService.addCash(member2,1_000_000,"충전__무통장입금");
            orderService.payByRestCashOnly(order2);
            orderService.refund(order2);

            Order order3 = helper.order(member2, Arrays.asList(
                            product1Option__RED_44,
                            product1Option__RED_55,
                            product2Option__BLACK_44,
                            product2Option__WHITE_44
                    )
            );
            orderService.payByRestCashOnly(order3);

            Order order4 = helper.order(member1, Arrays.asList(
                            product1Option__RED_44,
                            product2Option__WHITE_44
                    )
            );
            orderService.payByRestCashOnly(order4);
        };
    }
}
