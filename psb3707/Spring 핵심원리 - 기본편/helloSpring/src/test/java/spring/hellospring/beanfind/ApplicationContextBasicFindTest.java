package spring.hellospring.beanfind;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.hellospring.AppConfig;
import spring.hellospring.member.MemberService;
import spring.hellospring.member.MemberServiceImpl;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @DisplayName("빈 이름으로 조회")
    @Test
    void findBeanByName() {

        //given
        MemberService memberService = ac.getBean("memberService", MemberService.class);

        //when

        //then
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

    }

    @DisplayName("이름 없이 타입으로만 조회")
    @Test
    void findBeanByType() {

        //given
        MemberService memberService = ac.getBean( MemberService.class);

        //when

        //then
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

    }

    @DisplayName("구체 타입으로 조회")
    @Test
    void findBeanByName2() {

        //given
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);

        //when

        //then
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

    }

    @DisplayName("빈 이름으로 조회 X")
    @Test
    void findBeanByNameX(){

        //given

        //when

        //then
        Assertions.assertThatThrownBy(()-> ac.getBean("xxxx", MemberServiceImpl.class))
                .isInstanceOf(NoSuchBeanDefinitionException.class);

    }




}
