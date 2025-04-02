package spring.hellospring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.hellospring.discount.DiscountPolicy;
import spring.hellospring.discount.FixedDiscountPolicy;
import spring.hellospring.discount.RateDiscountPolicy;
import spring.hellospring.member.MemberRepository;
import spring.hellospring.member.MemberService;
import spring.hellospring.member.MemberServiceImpl;
import spring.hellospring.member.MemoryMemberRepository;
import spring.hellospring.order.OrderService;
import spring.hellospring.order.OrderServiceImpl;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixedDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
