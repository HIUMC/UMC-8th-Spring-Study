package hello.core.discount;

import hello.core.member.Member;

public interface DiscountPolicy {

    // return은 할인 해줄 금액
    int discount(Member member, int price);

}
