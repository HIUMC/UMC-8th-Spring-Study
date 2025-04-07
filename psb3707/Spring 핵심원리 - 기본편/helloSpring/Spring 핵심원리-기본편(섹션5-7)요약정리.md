# Spring 핵심원리 - 기본편

## 1. Spring 컨테이너

- ApplicationContext : 스프링 컨테이너
    - 이는 인터페이스이기에 다양한 구현체들을 가짐
    - 그중에서도 AnnotationConfigApplicationContext 일반적으로 많이 사용

        ```java
        // 스프링 컨테이너를 생성할 때는 구성 정보가 필요
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class); 
        ```

- Spring Bean 등록
    - 스프링 컨테이너는 파라미터로 넘어온 설정 클래스 정보를 바탕으로 스프링 빈 등록
- 의존관계 설정
    - 빈들이 모두 생성된 이후 동작
    - 설정 정보를 보고 의존관계를 주입


## 2. BeanFactory와 ApplicationContext의 차이

- BeanFactory
    - 스프링 컨테이너의 최상위 인터페이스
    - 스프링 빈을 관리하고 조회하는 역할을 담당
    - 기본적인 빈 관리 기능 제공
- ApplicationContext
    - BeanFactory 기능을 모두 상속받고 이에 더해 추가적인 기능들 제공
    - 애플리케이션 이벤트
        - 이벤트를 발행하고 구독하는 모델을 편리하게 지원
    - 메시지소스를 활용한 국제화 기능
        - 예를 들어서 한국에서 들어오면 한국어로, 영어권에서 들어오면 영어로 출력
    - 편리한 리소스 조회
        - 파일, 클래스패스, 외부 등에서 리소스를 편리하게 조회
    - 환경변수
        - 로컬, 개발, 운영등을 구분해서 처리


## 3. 싱글톤

- 만약 DI 컨테이너 없이 매 생성 요청마다 객체를 생성해야한다면 어떨까?
    - 50000 TPS 라면 매 초마다 50000개의 객체를 생성해야함 ⇒ 어마어마한 메모리 낭비..
- 그렇기에 객체를 1개만 생성해두고 공유하도록 설계 ⇒ 싱글톤 패턴
- 싱글톤 패턴의 문제점
    - 패턴을 구현하기 위한 코드가 추가적으로 필요
    - 의존관계상 클라이언트가 구체 클래스에 의존(DIP 위반)
    - 클라이언트가 구체 클래스에 의존(OCP 위반 가능성 존재)

## 4. 싱글톤 컨테이너

- 기존 싱글톤 패턴의 문제점들을 해결하면서, 객체 인스턴스를 오직 1개만 관리하는 것을 보장
- 스프링 컨테이너는 스프링 빈들을 싱글톤으로(오직 1개만 생성하고 관리) 관리한다 ⇒ **싱글톤 레지스트리 기능**
- 클라이언트의 요청마다 객체를 생성하는 것이 아닌 기존에 관리하던 객체를 재사용한다.
- 싱글톤 패턴 사용시 주의할 점!
    - 반드시 `무상태`로 설계해야한다.
    - 객체 인스턴스를 하나만 생성해서 여러 클라이언트가 공유하므로 공유변수가 존재할 경우 의도하지 않은 버그가 발생할 수 있다.

        ```java
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
                int price = statefulService1.getPrice(); // 금액 조회 전 B사용자의 주문으로 10000 -> 20000원으로 변경!
                System.out.println("price = " + price);
                Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
            }
        ```


## @Configuration과 바이트코드 조작

```java
@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixedDiscountPolicy();
        return new RateDiscountPolicy();
    }
}

// memberRepository()를 3번 호출하므로 memberRepository도 3번 생성되는거 아닐까? -> 싱글톤이 깨지는거 아닐까?
```

- 스프링은 바이트 코드를 조작하는 라이브러리를 사용!

    ```java
    // 바이트 코드 조작 라이브러리 : CGLIB
    bean = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$bd479d70  
    ```

- 실제로 빈으로 등록되는 것은 `AppConfig$$EnhancerBySpringCGLIB$$bd479d70`  이다!
- `@Bean`이 붙은 메서드마다 이미 스프링 빈이 존재하면 존재하는 빈을 반환하고, 스프링 빈이 없으면 생성해서 스프링 빈으로 등록하고 반환하는 코드가 동적으로 만들어짐
- 하지만 @Configuration 어노테이션이 없이 @Bean 어노테이션만 존재하면 실제 클래스가 빈으로 등록됨! → 싱글톤이 깨진다!

## 컴포넌트 스캔

- 설정 정보가 없어도 `@ComponentScan` 어노테이션으로 스프링 빈 등록을 자동화
- `@Component` 어노테이션이 붙은 클래스들을 자동으로 빈 등록!
    - 이때, `@Configuration` 어노테이션도 자동으로 등록된다. ⇒ 예제 코드에서는 설정 정보가 중복 되지 않도록 제외

        ```java
         @ComponentScan(
                excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = 
        Configuration.class))
        ```

- But, 빈 등록은 자동화 되었지만, 의존관계 주입도 해당 설정 클래스에서 해결해야 한다.
    - 이때, 필요한 것이 `@Autowired` 어노테이션! ⇒ 생성자 위에 붙어, 필요한 의존성들을 자동으로 주입
    - 스프링 컨테이너는 기본 조회 전략으로 타입이 같은 빈을 찾아, (ac.getBean(MemberRepository.class)) 의존성 주입
- 기본적으로 탐색 위치는 `@ComponentScan` 어노테이션이 붙은 설정 클래스가 있는 패키지의 모든 하위 패키지를 스캔한다.

    ```java
    @ComponentScan(
            basePackages = "hello.core" // 디폴트
     }
    ```

- 중복 등록과 충돌
    - 수동 빈 등록과 자동 빈 등록에서 빈 이름이 충돌하는 경우
        - 수동 빈 등록이 우선권을 가진다.
    - 일반적으로 이런 상황이 벌어지지 않도록 예방하는 것이 우선! ⇒ 버그 발생 시 해결하기 매우 어려움..