package spring.hellospring.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import spring.hellospring.AppConfig;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @DisplayName("싱글톤 패턴은 무상태로 설계해야한다.")
    @Test
    void statefulServiceSingleton() {

        //given
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //when
        statefulService1.order("userA", 10000); // ThreadA : A사용자 10000원 주문
        statefulService2.order("userB", 20000); // ThreadB : B사용자 20000원 주문

        //then
        int price = statefulService1.getPrice();
        System.out.println("price = " + price);
        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}