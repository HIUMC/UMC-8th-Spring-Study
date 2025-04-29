package spring.hellospring.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import spring.hellospring.discount.FixedDiscountPolicy;
import spring.hellospring.member.Grade;
import spring.hellospring.member.Member;
import spring.hellospring.member.MemoryMemberRepository;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {


    @DisplayName("순수 자바 코드를 통한 테스트")
    @Test
    void createOrder() {

        //given
        MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();

        memoryMemberRepository.save(new Member(1L, "name", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(memoryMemberRepository, new FixedDiscountPolicy());
        //when
        Order order = orderService.createOrder(1L, "itemA", 10000);

        //then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}