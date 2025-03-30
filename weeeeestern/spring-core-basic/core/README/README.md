# umc 2주차 README

# Section 2. 객체 지향 설계와 스프링

### 스프링

- 스프링 프레임 워크
    - 스프링 DI 컨테이너, AOP, 이벤트
    - 스프링 MVC, 스프링 WebFlux
    - 트랜잭션, JDBC, ORM 지원, XML 지원
    - 캐시, 이메일, 원격접근, 스케줄링
    - 스프링 기반 테스트
    
- 스프링 부트 : 스프링 설정을 쉽게..!
    - Tomcat 서버가 내장되어 있다.
    - 외부 라이브러리 호환 버전 관리
    - 스프링보다 간결한 설정 환경

- 스프링
    - 스프링 DI 컨테이너
    - 스프링 프레임워크 전체
    - 스프링 부트, 스프링 프레임워크를 포함한 스프링 생태계..
    - 스프링은 좋은 객체 지향 애플리케이션을 개발할 수 있게 도와주는 프레임워크

### 객체지향

- 추상화, 캡슐화, 상속
- **다형성** Polymorphism : 클라이언트를 변경하지 않고, 서버의 구현 기능을 유연하게 바꿀 수 있다.
    - **역할(인터페이스)**과 **구현(객체, 클래스)**으로 구분
        - 클라이언트는 내부 구조를 몰라도 된다.  대상의 **역할**(**인터페이스**)만 알면 된다.
        - 클라이언트는 구현 대상을 변경해도 영향 받지 않는다.
        → 구현이 계속 많아질 수 있음. 확장성 多
        - 상속을 자유롭게 다중으로 구현 가능
    - 오버라이딩 : 부모 클래스로부터 상속받은 메소드를 자식 클래스에서 **재정의** 한 것.
        - 인터페이스의 구현 인스턴스를 실행 시점에 유연하게 변경할 수 있다.
    - 오버로딩 : 메소드의 이름이 같지만, 매개변수의 개수나 타입이 달라야 한다. 리턴값은 조건이 아님.

다형성 만으로는 OCP와 DIP를 지킬 수 없다.

### **SOLID**

- SRP 단일 책임 원칙:
    - 한 클래스는 하나의 책임만 가져야 한다 →변경이 있을 때, 파급이 가능한 가장 적어야 함 ex. AppConfig
- **OCP** 개방-폐쇄 원칙:
    - 소프트웨어 요소는 확장에는 열려 있고, 변경에는 닫혀있어야 한다.
    - 기존 코드를 고치지 않고, 새 기능을 추가할 수 있어야 한다.
    - DiscountPolicy 인터페이스를 사용하는 OrderService가 있을 때,
    FixDiscountPolicy, RateDiscountPolicy 같은 구현체를 바꾸더라도
    OrderService는 변경하지 않고, 설정 파일(AppConfig)만 바꾸면 됨.
- LSP 리스코프 치환 원칙성
    - 성능이 좋지 않아도 정해진 규약 지키기
- ISP 인터페이스 분리 원칙
- **DIP** 의존관계 역전 원칙
    - 구현 클래스에 의존하지 말고, 인터페이스에 의존하기. (구체화에 의존하지말고, **추상화**에 의존하기)

- DI (Dependency Injection) : 의존관계, 의존성 주입

※ 인터페이스는 추상화라는 비용이 발생한다. (ex. 개발자가 어떤 객체를 썼는지 코드를 한 번 더 열어봐야 함) → 따라서 기능을 확장할 가능성이 없다면, 구체적인 클래스를 직접 사용하는 게 좋다.

# Section3. 스프링 핵심원리 이해 1 - 예제 만들기

## <비즈니스 요구사항>

- 회원
    - 회원을 가입하고 조회할 수 있다.
    - 회원은 일반과 VIP 두 가지 등급이 있다.
    - 회원 데이터는 자체 DB를 구축할 수 있고, 외부 시스템과 연동할 수 있다. (미확정)
- 주문과 할인 정책
    - 회원은 상품을 주문할 수 있다.
    - 회원 등급에 따라 할인 정책을 적용할 수 있다.
    - 할인 정책은 모든 VIP는 1000원을 할인해주는 고정 금액 할인을 적용해달라. (나중에 변경 될 수 있다.)
    - 할인 정책은 변경 가능성이 높다. 회사의 기본 할인 정책을 아직 정하지 못했고, 오픈 직전까지 고민을 미루고 싶다. 최악의 경우 할인을 적용하지 않을 수도 있다. (미확정)

### <회원 도메인 설계>

![image.png](image.png)

![image.png](image%201.png)

*Impl : 구현

- 클래스 다이어그램 → 정적
    
    ![image.png](image%201.png)
    
    *Impl : 구현
    

![image.png](image%202.png)

- 객체(실제 참조되는 클래스의 인스턴스) 다이어그램 → 동적
    
    ![image.png](image%202.png)
    

---

### <회원 도메인 개발>

- 내가 원하는 **속성** ex. grade → **enum** 으로 만들어두기
- 클래스 → 생성자, getter, setter 만들기
- 인터페이스, 구현체는 원래 다른 패키지에 둔다
    - 인터페이스 : 기능 선언
    - 구현체 : 인터페이스에 선언된 기능 구현
        - 데이터 **저장**을 해야하니까 **HashMap** 만들기

<Member package>

멤버 클래스 → id, name, grade

인터페이스와 구현체

- MemberRepository
    - MemoryMemberRepository
    - DBMemberRepository
- MemberService
    - MemberServiceImpl  → MemberRepository **인터페이스의 구현 객체**가 필요하다..!!
        - ~~없으면 NullPointException 터짐~~
    
    //`private final MemberRepository memberRepository = **new MemoryMemberRepository()**;`
    
    → 다형성 有 : MemberRepository의 기능들을 MemoryMemberRepository에 적용시킬 수 있다.
    
    ※ DIP 위반 : 실제 할당되는 부분(new ~)이 인터페이스가 아닌, 구현체를 의존하고 있다.
    

- TEST는 Junit 이용!!

---

### <주문과 할인 도메인 설계>

- 주문과 할인 정책
    - 회원은 상품을 주문할 수 있다.
    - 회원 등급에 따라 할인 정책을 적용할 수 있다.
    - 할인 정책은 모든 VIP는 1000원을 할인해주는 고정 금액 할인을 적용해달라. (나중에 변경 될 수 있다.)
    - 할인 정책은 변경 가능성이 높다. 회사의 기본 할인 정책을 아직 정하지 못했고, 오픈 직전까지 고민을 미루고 싶다. 최악의 경우 할인을 적용하지 않을 수 도 있다. (미확정)

![image.png](image%203.png)

![image.png](image%204.png)

### <주문 도메인 개발>

- OrderServiceImpl 의 메소드 implements 인터페이스
    - 주문 생성해서, 적용 사항(회원가입과 할인) 적용한 주문 결과를 반환
        - MemberRepository의  MemoryMemberRepository 인스턴스가 필요함
        - DiscountPolicy의 FixDiscountPolicy 인스턴스가 필요함
    - 주문 생성 요청이 오면
        - 회원 정보 조회
        - 할인 정책에 회원 정보 넘기기
        - 회원 정보에 알맞는 할인된 가격(discountPrice) 받기
        - 최종 생성된 주문 정보 반환

---

### 정리!

- 객체를 찍어낼 **클래스** -> 생성자, getter, setter 정의 필요.
- 인터페이스(Service) ← 구현체(Impl)
    - **구현체**에서 메소드 재정의 **@Override**
    
- 레포리토지 인터페이스에서는
    - DB에서 데이터 가져오기, JPA의 자바 엔티티와 DB 매핑 관리
    - CRUD 메서드 제공
        - 메서드가 호출되면, JPA는 그 데이터를 SQL문을 통해 DB에서 조회하고, 엔티티 객체로 변환시켜서 반환한다.

- 서비스 인터페이스에서는
    - 실제 비즈니스 로직(예. 회원가입, 아이디로 회원 찾기 ..) 제공

# Section 4. 스프링 핵심 원리 이해 2 - 객체 지향 원리 적용

![image.png](image%205.png)

                                                                                       ??..

![image.png](image%206.png)

할인 정책 인터페이스의 정률 Rate 할인 정책 구현체를 추가하자!

- 역할과 구현을 분리하고, 다형성을 활용했지만,
    - 구현체(OrderServiceImpl)가 인터페이스(DiscountPolicy) 뿐만 아니라, 그의 구현체(RateDiscountPolicy)도 의존하고 있다.
        
        → **DIP** (의존관계 원칙) 위반
        
    - 할인 정책을 변경하는데, OrderServiceImpl 코드를 고쳐야 한다. (참조를 바꿔야 돼서)
        
        → **OCP** (변경에는 폐쇄적이여야 함) 위반
        

남주인공 배역의 “배우”는 여주인공 배역의 배우를 초빙하면 안 된다. 
배역을 수행하는 것, 공연에만 집중해야 한다.  (*배역=인터페이스, 배우=구현체) 

그니까 여태까지 뽑는 사람은 안 만들었고, 배역과 배우만 만든 것이다!

→ 공연 기획자 라는 초빙하는 일을 하는 인터페이스를 만들어야 한다! (SRP OK)

## AppConfig

- AppConfig 등장 :애플리케이션의 전체 동작 방식을 구성(config)하기 위해, **설정 클래스**를 만들자.
    
    → AppConfig 는 배역에 맞는 배우를 직접 뽑는다.
         즉, 애플리케이션의 실제 동작에 필요한 구현 객체를 AppConfig에서 주입한다.
    
    - 가의존 구현체 코드에서 피의존 구현체를 의존하던 부분을 삭제하고, 
    인터페이스만 남겨둔다 (DIP OK)
    - AppConfig 코드에 **인터페이스의 생성자**를 만든다. → 의존 관계를 **생성자로 주입한다.**
    - 생성자의 **반환값**에서, 가의존 구현체를 new 인스턴스 생성하여,
    피의존 구현체를 **인자**로 넣어준다. → 의존 관계 주입

→ MemberServiceImpl 구현은 이제부터 의존 관계에 대한 고민은 **외부**에 맡기고, 실행에만 집중

![image.png](image%207.png)

※ OrderServiceImpl 은 두 가지 인터페이스(MemberRepository, DiscountPolicy)를 의존해야 됨

- `private final MemberRepository memberRepository; 
 private final DiscountPolicy discountPolicy;`
    - 일반 필드(클래스)는 객체가 만들어질 때 초기화 된다. → 컴파일 타임에 생성자가 하나도 없으면 기본 생성자가 자동 추가됨.
    - **final 필드**는 생성 시점과 동시에 **명시적으로** 값이 할당돼야 한다! → **생성자**에서 꼭 초기화해줘야함

※ 테스트 환경에서는 각 인터페이스의 필드를 한번만 new 해서 쓰게 되면, 여러 테스트가 같은 인스턴스를 공유하게 된다. → **@BeforeEach**로 각 테스트 실행 전, fresh하게 초기화를 해줘야 함

- AppConfig 리팩터링
    
    ![image.png](image%208.png)
    
    - 인터페이스의 다른 구현체로 교체할 때, AppConfig 의 한 부분만 변경하면 된다.
    - 리팩토링 전 : 인자에 `new` 로 피의존 구현체 객체를 직접 생성했는데,
    - 리팩토링 후 : 인터페이스 생성자를 **모두** 명시적으로 만들어줌. 중복 제거!
                              역할과 구현 클래스가 한 눈에 들어옴.
    
    - 구성(Config) 영역에만 영향 有, 사용 영역에는 영향 無
    
    ![image.png](image%209.png)
    
    ```java
    public class AppConfig {
    
        public MemberService memberService(){
            return new MemberServiceImpl(memberRepository()); 
        }
    
        private MemberRepository memberRepository() {
            return new MemoryMemberRepository();
        }
    
        public OrderService orderService(){
            return new OrderServiceImpl(memberRepository(), discountPolicy());
        }
    
        public DiscountPolicy discountPolicy(){
            return new RateDiscountPolicy();
        }
    }
    ```
    
    - Service들은 인터페이스, Impl 은 실제 비즈니스 로직 구현체
    - 각각 **생성자 주입 방식**으로 의존성을 주입하고 있다.
        - 서비스는 레포리토지를 이용하여 데이터를 처리해야 한다. 
        (따라서 서비스는 레포에 의존)

---

- 제어의 역전 IoC (Inversion of Control)

구현 객체는 자신의 로직을 실행하는 역할만 담당하고, 프로그램의 **제어 흐름**은 **AppConfig**가 가져간다.

- 프레임워크 ↔ 라이브러리
    - 프레임워크 :  내가 작성한 코드를 제어하고, 대신 실행하면! ex. @Test의 Junit
    - 라이브러리 : 내가 작성한 코드가 직접 제어하면!

- 의존관계 주입
    - 정적인 클래스 의존 관계 : import ~, implements 만 보고 쉽게 의존 관계 파악
    - 실행 시점에 결정되는, 동적인 **객체 의존 관계** : 런타임 중에 실제 구현 객체가 생성되고, 그 참조값을 클라이언트에 전달한다.

→ 의존 관계 주입 = 런타임 중에 **외부**에서 구현 객체를 생성하여, 클라이언트에 전달하면, 클라이언트와 서버의 실제 의존 관계가 연결 되는 것

- IoC 컨테이너, DI 컨테이너
    - AppConfig 같이 구현 객체 생성하고, 제어 흐름을 결정하는 애
    - 의존 관계 주입에 초점(DI 컨테이너)  ex. Spring
    - Impl 입장에서는 외부에서 의존성을 주입 받는 것 같다고 해서 의존성 주입.

---

## Spring 전환

- AppConfig 를 스프링 중심으로 바꾸기
    - @Configuration  : 구성 정보, 설정 정보 (AppConfig에다가 설정을 구성한다는 뜻)
    - @Bean : 스프링 컨테이너에 등록 (기본적으로 메소드 이름을 빈의 이름으로 등록)

```java
ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
```

- ApplicationContext : 스프링 컨테이너, @Bean 객체들을 스프링 컨테이너에 등록
- @Configuration 붙은 AppConfig 에 있는 환경 설정 정보를 이용
- 빈 꺼내쓰기 : `applicationContext.getBean(빈 이름, 지정 클래스)`