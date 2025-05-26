package spring.hellospring.autowired;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.hellospring.AppConfig;
import spring.hellospring.AutoAppConfig;
import spring.hellospring.discount.DiscountPolicy;
import spring.hellospring.member.Grade;
import spring.hellospring.member.Member;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AllBeanTest {


    @DisplayName("등록된 빈들을 리스트로 한번에 조회 가능하다.")
    @Test
    void findAllBean() {

        //given
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);

        Member member = new Member(1L, "name", Grade.VIP);

        //when
        int result1 = discountService.discount(member, 10000, "fixedDiscountPolicy");
        int result2 = discountService.discount(member, 20000, "rateDiscountPolicy");

        //then
        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(result1).isEqualTo(1000);

        assertThat(result2).isEqualTo(2000);
    }

    static class DiscountService{

        private Map<String, DiscountPolicy> policyMap;

        private List<DiscountPolicy> policyList;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policyList) {
            this.policyMap = policyMap;
            this.policyList = policyList;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policyList = " + policyList);
        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);

            return discountPolicy.discount(member, price);
        }
    }
}
