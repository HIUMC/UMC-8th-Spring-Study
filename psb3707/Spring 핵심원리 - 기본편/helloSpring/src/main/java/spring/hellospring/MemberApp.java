package spring.hellospring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.hellospring.member.Grade;
import spring.hellospring.member.Member;
import spring.hellospring.member.MemberService;
import spring.hellospring.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {

//        AppConfig appConfig = new AppConfig();

//        MemberService memberService = appConfig.memberService();
//        MemberServiceImpl memberService = new MemberServiceImpl();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);


        Member memberA = new Member(1L, "memberA", Grade.VIP);

        memberService.join(memberA);

        Member findMember = memberService.findMember(1L);

        System.out.println("new member = " + memberA.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}
