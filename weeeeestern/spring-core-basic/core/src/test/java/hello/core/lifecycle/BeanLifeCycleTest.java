package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        // 내가 만든 설정정보로 스프링 컨테이너 만들기
        // 구현체 타입이 아닌, 인터페이스 타입으로 선언
        // 상속 관계 ApplicationContext -> ConfigurableAppli~ -> AnnotationConfigAppli~
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {
       // @Bean(initMethod = "init", destroyMethod = "close")
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient(); // 생성자 호출
            networkClient.srtUrl("http://hello-spring.dev");
            return networkClient;
        } // 수동 빈 등록 : 직접 객체를 생성하고, 설정하고, return 결과를 스프링에 등록함
    }
}
