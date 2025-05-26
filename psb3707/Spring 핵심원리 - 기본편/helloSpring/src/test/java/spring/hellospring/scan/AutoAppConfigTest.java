package spring.hellospring.scan;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.hellospring.AutoAppConfig;
import spring.hellospring.member.MemberService;

public class AutoAppConfigTest {


    @DisplayName("컴포넌트 스캔을 통한 자동 빈 등록")
    @Test
    void basicScan(){

        //given
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        MemberService memberService = ac.getBean(MemberService.class);

        //when then
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);

    }
}
