package heelo.core;

import heelo.core.member.Grade;
import heelo.core.member.Member;
import heelo.core.member.MemberService;
import heelo.core.member.MemberServiceImpl;
import heelo.core.order.Order;
import heelo.core.order.OrderServiceImpl;
import heelo.core.order.OrderService;

public class OrderApp {

    public static void main(String[] args) {

        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        //MemberService memberService = new MemberServiceImpl(null);
        //OrderService orderService = new OrderServiceImpl(null);

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 20000);
        System.out.println("order = " + order);
    }
}
