package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;


class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void AfterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("sparc");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("mips");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("nips");
        repository.save(member2);

       Member result =  repository.findByName("mips").get();
        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("sparc");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("nips");
        repository.save(member2);

        List<Member> result = repository.findAll();

        Assertions.assertThat(result).hasSize(2);

    }

}
