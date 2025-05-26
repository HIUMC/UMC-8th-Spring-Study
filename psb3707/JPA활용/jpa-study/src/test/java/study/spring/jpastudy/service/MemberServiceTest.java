package study.spring.jpastudy.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.spring.jpastudy.domain.Address;
import study.spring.jpastudy.domain.Member;
import study.spring.jpastudy.repository.MemberRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;


    @DisplayName("")
    @Test
    @Rollback(false)
    void 회원가입(){

        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long savedId = memberService.join(member);

        //then
        Assertions.assertThat(member.getId()).isEqualTo(savedId);
    }


    @DisplayName("")
    @Test
    void 중복_회원_예외(){

        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);
        //then
        Assertions.assertThatThrownBy(() -> memberService.join(member2))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @DisplayName("")
    @Test
    @Rollback(false)
    void embeddableaClass(){

        //given
        Address address = new Address("경기도", "군포시", "금산로");

        Member member = new Member();
        Member member1 = new Member();

        member.setName("kim");
        member.setAddress(address);

        member1.setName("park");
        member1.setAddress(address);

        memberService.join(member);
        memberService.join(member1);

        //when
        Member findMember = memberService.findOne(member.getId());
        findMember.getAddress().setCity("서울시");

        //then
    }
}