package hello.hello_spring;

import hello.hello_spring.repository.*;
import hello.hello_spring.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    // 스프링 데이터 JPA 사용 시
    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // JPA 사용 시
//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    // 순수 Jdbc & JdbcTemplate 사용 시
//    private final DataSource dataSource;
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository() {
//        // 메모리 데이터베이스 사용
//        // return new MemoryMemberRepository();
//
//        // 순수 JDBC
//        // return new JdbcMemberRepository(dataSource);
//
//        // 스프링 JdbcTemplate
//        // return new JdbcTemplateMemberRepository(dataSource);
//
//        // JPA
//        return new JpaMemberRepository(em);
//    }
}
