package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        // 각 테스트를 돌릴 때마다 저장소에 같은 이름의 변수가 저장되면 오류 발생.
        // 따라서 한 테스트가 끝나면, 저장소를 비워줘야 함
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member(); // 테스트 용 가짜 회원
        member.setName("spring");
        repository.save(member); // 저장 기능 이용

        Member result = repository.findById(member.getId()).get();
        // 저장소에서 가짜 회원 조회

        System.out.println("저장 테스트 후 조회 결과 = " + (result == member));
        // 만든 가짜 회원과, 그 회원을 저장한 저장소의 회원이 같은 객체인가?
        // 완전히 똑같은 메모리 주소를 가리키는가
        Assertions.assertEquals(member, result); // sout 쓰지 않고 같은 객체인지 판단하기
        org.assertj.core.api.Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring2").get();

        org.assertj.core.api.Assertions.assertThat(member2).isEqualTo(result);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member member3 = new Member();
        member3.setName("spring3");
        repository.save(member3);

        List<Member> result = repository.findAll();

        org.assertj.core.api.Assertions.assertThat(result.size()).isEqualTo(3);

    }
}
