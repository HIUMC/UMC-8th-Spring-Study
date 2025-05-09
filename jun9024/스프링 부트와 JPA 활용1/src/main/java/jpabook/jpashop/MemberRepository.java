package jpabook.jpashop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository //컴포넨트 스캔의 대상->자동으로 스프링 빈에 등록
public class MemberRepository {

    @PersistenceContext //이 어노테이션 안에 있으면 스프링 부트가 엔티티 메니저를 주입해줌.
    EntityManager em;

    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }

    public Member find(Long id){
        return em.find(Member.class, id);
    }
}
