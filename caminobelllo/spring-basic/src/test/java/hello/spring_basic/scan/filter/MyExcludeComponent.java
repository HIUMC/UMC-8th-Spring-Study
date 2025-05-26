package hello.spring_basic.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyExcludeComponent {
    // 이 어노테이션이 붙으면 컴포넌트 스캔 대상에서 제외
}
