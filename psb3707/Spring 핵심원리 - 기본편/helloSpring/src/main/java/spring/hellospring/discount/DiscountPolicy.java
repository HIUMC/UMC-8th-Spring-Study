package spring.hellospring.discount;

import spring.hellospring.member.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}
