package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.JdbcMemberRepository;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired MemberService service;
    @Autowired MemberRepository repository;

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("hello");
        //when
        Long savedId = service.join(member);
        //then
        Member findMember = service.findOne(savedId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("hello");

        Member member2 = new Member();
        member2.setName("hello");
        //when
        service.join(member1);
        org.junit.jupiter.api.Assertions.assertThrows(IllegalStateException.class, () -> service.join(member2));

    }
}