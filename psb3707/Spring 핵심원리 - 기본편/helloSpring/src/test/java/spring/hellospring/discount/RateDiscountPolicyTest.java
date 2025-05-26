package spring.hellospring.discount;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import spring.hellospring.member.Grade;
import spring.hellospring.member.Member;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();

    @DisplayName("VIP는 10%이 적용되어야 한다.")
    @Test
    void vip_o(){

        //given
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when
        int discountPrice = rateDiscountPolicy.discount(member, 10000);

        //then
        assertThat(discountPrice).isEqualTo(1000);
    }

    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다.")
    @Test
    void vip_x(){

        //given
        Member member = new Member(2L, "memberB", Grade.BASIC);

        //when
        int discountPrice = rateDiscountPolicy.discount(member, 10000);

        //then
        assertThat(discountPrice).isEqualTo(1000);

    }

}