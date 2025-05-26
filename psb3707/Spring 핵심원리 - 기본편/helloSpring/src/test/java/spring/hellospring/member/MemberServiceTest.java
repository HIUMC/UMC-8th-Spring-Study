package spring.hellospring.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import spring.hellospring.AppConfig;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

//    MemberService memberService = new MemberServiceImpl();

    MemberService memberService;


    @BeforeEach
    void beforeEach() {
        AppConfig appConfig = new AppConfig();

        memberService = appConfig.memberService();
    }

    @DisplayName("사용자 정보를 생성하여 데이터베이스에 저장한다.")
    @Test
    void join(){

        //given
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then
        assertThat(findMember).isEqualTo(member);
    }
}