package spring.hellospring.beanfind;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.hellospring.member.MemberRepository;
import spring.hellospring.member.MemoryMemberRepository;

import java.util.Map;

public class ApplicationContextSameBeanFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);


    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 중복 오류가 발생한다.")
    @Test
    void findBeanByTypeDuplicate(){

        //given

        //when

        //then
        Assertions.assertThatThrownBy(()-> ac.getBean(MemberRepository.class))
                .isInstanceOf(NoUniqueBeanDefinitionException.class);

    }

    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 빈 이름을 지정하면 된다.")
    @Test
    void findBeanByName(){

        //given
        MemberRepository bean = ac.getBean("memberRepository1", MemberRepository.class);

        //when

        //then
        Assertions.assertThat(bean).isInstanceOf(MemberRepository.class);

    }

    @DisplayName("특정 타입을 모두 조회하기")
    @Test
    void findAllBeanByType(){

        //given
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        //when then
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }

    }

    @Configuration
    static class SameBeanConfig{

        @Bean
        public MemberRepository memberRepository1(){
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2(){
            return new MemoryMemberRepository();
        }
    }

}
