package spring.hellospring.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import spring.hellospring.AppConfig;
import spring.hellospring.member.Grade;
import spring.hellospring.member.Member;
import spring.hellospring.member.MemberService;
import spring.hellospring.member.MemberServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;
    
    @BeforeEach
    void beforeEach() {
        AppConfig appConfig = new AppConfig();
        orderService = appConfig.orderService();
        memberService = appConfig.memberService();
    }

    @DisplayName("주믄을 생성한다. 이때, VIP회원의 경우 할인율은 지정된 할인율을 따른다.")
    @Test
    void createOrder(){

        //given
        Long memberId = 1L;
        Member memberA = new Member(memberId, "memberA", Grade.VIP);


        //when
        memberService.join(memberA);
        Order order = orderService.createOrder(memberId, "itemA", 10000);

        //then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}