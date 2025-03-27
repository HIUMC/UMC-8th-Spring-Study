package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 메서드 실행이 끝날때마다 어떤 동작을 함
    @AfterEach
    public void afterEach() {
        repository.clearStore(); // 저장소를 지움
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get(); // optional에서 값을 꺼낼때는 get()
        // result와 member가 같은지 확인 -> 돌렸을 때 초록불이면 ok
        Assertions.assertEquals(result, member); // 방법 1
        assertThat(member).isEqualTo(result); // 방법 2 <- 많이 씀
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        // shift + f6 리네임
        Member member2 = new Member();
        member2.setName("spring1");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
       Member member1 = new Member();
       member1.setName("spring1");
       repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring1");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
