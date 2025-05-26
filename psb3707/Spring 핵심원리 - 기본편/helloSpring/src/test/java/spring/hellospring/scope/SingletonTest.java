package spring.hellospring.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import spring.hellospring.AppConfig;

public class SingletonTest {


    @DisplayName("")
    @Test
    void singletonBeanFind() {

        //given
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SingletonBean.class);

        //when
        SingletonBean bean1 = context.getBean(SingletonBean.class);
        SingletonBean bean2 = context.getBean(SingletonBean.class);

        //then
        System.out.println("bean1 = " + bean1);
        System.out.println("bean2 = " + bean2);
        Assertions.assertThat(bean1).isSameAs(bean2);

        context.close();
    }

    @Scope("singleton")
    static class SingletonBean {
        @PostConstruct
        void postConstruct() {
            System.out.println("postConstruct");
        }

        @PreDestroy
        void preDestroy() {
            System.out.println("preDestroy");
        }
    }
}
