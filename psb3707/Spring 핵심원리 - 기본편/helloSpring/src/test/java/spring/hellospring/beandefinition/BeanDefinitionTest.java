package spring.hellospring.beandefinition;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.hellospring.AppConfig;

public class BeanDefinitionTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);


    @DisplayName("빈 설정 메타정보 확인")
    @Test
    void findApplicationBean(){

        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        //when then
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {

                System.out.println("beanDefinitionName = " + beanDefinitionName  + " beanDefinition = " + beanDefinition);
            }

        }

    }
}
