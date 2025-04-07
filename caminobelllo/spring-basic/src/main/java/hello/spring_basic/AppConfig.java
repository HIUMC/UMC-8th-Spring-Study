package hello.spring_basic;

import hello.spring_basic.discount.DiscountPolicy;
import hello.spring_basic.discount.RateDiscountPolicy;
import hello.spring_basic.member.MemberRepository;
import hello.spring_basic.member.MemberService;
import hello.spring_basic.member.MemberServiceImpl;
import hello.spring_basic.member.MemoryMemberRepository;
import hello.spring_basic.order.OrderService;
import hello.spring_basic.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    // 메서드명을 통해 역할과 구현이 명확하게 구분됨

    @Bean
    public MemberService memberService() {

        System.out.println("call AppConfig.memberService");

        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {

        System.out.println("call AppConfig.orderService");

        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public MemberRepository memberRepository() {

        System.out.println("call AppConfig.memberRepository");

        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        // return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}
