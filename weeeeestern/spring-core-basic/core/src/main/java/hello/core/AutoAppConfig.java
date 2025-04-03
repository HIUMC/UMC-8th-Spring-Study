package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "hello.core", // 디폴트 값이 이 파일의 package 위치!
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes =
            Configuration.class)
)
public class AutoAppConfig {

}
