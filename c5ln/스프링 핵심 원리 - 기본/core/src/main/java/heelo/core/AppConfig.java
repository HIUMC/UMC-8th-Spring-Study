package heelo.core;

import heelo.core.discount.DiscountPolicy;
import heelo.core.discount.FixDiscountPolicy;
import heelo.core.discount.RateDiscountPolicy;
import heelo.core.member.MemberRepository;
import heelo.core.member.MemberService;
import heelo.core.member.MemberServiceImpl;
import heelo.core.member.MemoryMemberRepository;
import heelo.core.order.OrderService;
import heelo.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService()
    {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(),discountPolicy());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }

}
