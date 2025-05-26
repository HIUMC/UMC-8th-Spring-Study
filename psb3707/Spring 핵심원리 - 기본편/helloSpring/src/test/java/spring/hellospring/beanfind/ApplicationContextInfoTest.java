package spring.hellospring.beanfind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.hellospring.AppConfig;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @DisplayName("모든 빈 출력하기")
    @Test
    void findAllBeans(){

        //given
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        //when then
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);

            System.out.println("name = " + beanDefinitionName  + " object = " + bean);
        }

    }

    @DisplayName("애플리케이션 빈 출력하기")
    @Test
    void findApplicationBeans(){

        //given
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        //when then
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);

                System.out.println("name = " + beanDefinitionName  + " object = " + bean);
            }

        }

    }
}
