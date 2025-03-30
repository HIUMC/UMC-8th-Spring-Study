package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    private MemberService memberService= new MemberServiceImpl();

    @Test
    public void join() {
        //given
        Member member = new Member(1L,"memberA",Grade.VIP);
        //when
        memberService.join(member);
        Member findMember = memberService.join(1L);
        //then
        Assertions.assertThat(findMember).isEqualTo(member);
    }

    @Test
    public void findById() {
        //given
        Member member = new Member(1L,"memberA",Grade.VIP);

        //when

        //then
    }
}
