package spring.hellospring.discount;

import org.springframework.stereotype.Component;
import spring.hellospring.member.Grade;
import spring.hellospring.member.Member;

@Component
public class FixedDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        }

        return 0;
    }
}
