package spring.hellospring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.hellospring.member.Grade;
import spring.hellospring.member.Member;
import spring.hellospring.member.MemberService;
import spring.hellospring.member.MemberServiceImpl;
import spring.hellospring.order.Order;
import spring.hellospring.order.OrderService;
import spring.hellospring.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {

//        MemberServiceImpl memberService = new MemberServiceImpl();
//        OrderServiceImpl orderService = new OrderServiceImpl();

//        AppConfig appConfig = new AppConfig();
//        OrderService orderService = appConfig.orderService();
//        MemberService memberService = appConfig.memberService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class); // 스프링 컨테이너
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        Long memberId = 1L;
        Member memberA = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(memberA);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        System.out.println("order = " + order);
    }
}
