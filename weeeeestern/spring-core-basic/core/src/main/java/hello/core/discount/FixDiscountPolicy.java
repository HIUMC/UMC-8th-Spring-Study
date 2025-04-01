package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class FixDiscountPolicy implements DiscountPolicy {

    private int fixDiscountAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade()== Grade.VIP) // enum 타입은 '=='으로 비교
            return fixDiscountAmount;
        else
            return 0;
    }
}
