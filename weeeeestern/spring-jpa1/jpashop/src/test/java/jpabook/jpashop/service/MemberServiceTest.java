package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void join() throws Exception {
        Member member = new Member();
        member.setName("Kim");

        Long saveId = memberService.join(member);

        Assertions.assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test
    void duplicateMemberException() throws Exception {
        Member member1 = new Member();
        member1.setName("Kim");

        Member member2 = new Member();
        member2.setName("Kim");

        memberService.join(member1);

        assertThrows(IllegalArgumentException.class, () ->
                memberService.join(member2));
    }
}