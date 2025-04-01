package hello.spring_basic.discount;

import hello.spring_basic.member.Grade;
import hello.spring_basic.member.Member;

public class FixDiscountPolicy implements DiscountPolicy{

    // 고정 금액(1000원) 할인
    private int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {

        // VIP : 1000원 할인, 아니면 할인 없음
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
