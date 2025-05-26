package spring.hellospring.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class PrototypeTest {


    @DisplayName("")
    @Test
    void prototypeBeanFind() {

        //given
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find prototypeBean1");
        PrototypeBean bean1 = ac.getBean(PrototypeBean.class); // 이때 생성!
        System.out.println("find prototypeBean2");
        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);

        //when
        System.out.println("bean1 = " + bean1);
        System.out.println("bean2 = " + bean2);


        //then
        Assertions.assertThat(bean1).isNotSameAs(bean2);
        ac.close();
    }

    @Scope("prototype")
    static class PrototypeBean {
        @PostConstruct
        void postConstruct() {
            System.out.println("postConstruct");
        }

        @PreDestroy // 호출 하지 않음
        void preDestroy() {
            System.out.println("preDestroy");
        }
    }
}
