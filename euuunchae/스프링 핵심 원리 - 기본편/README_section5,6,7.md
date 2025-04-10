### Section 5 - 스프링 컨테이너와 스프링 빈

[스프링 컨테이너 생성]

ApplicationContext : 스프링 컨테이너. 인터페이스(다형성 구현되어 있음)

만드는 방법 : XML 기반으로 만들기 or **어노테이션 기반 자바 설정 클래스로 만들기**

스프링 컨테이너 생성하면 안에 스프링 빈 저장소 있음. key : 빈 이름, value : 빈 객체

스프링 컨테이너 생성할 때, AppConfig.class를 파라미터로 넣는 이유 → 구성 정보를 지정해주어야 하기 때문! (AppConfig.class를 구성 정보로 지정한다)

스프링 빈 저장소에 @Bean 붙은 객체를 전부 호출하고 등록함. ‘빈 이름’에는 주로 메서드 이름을 많이 사용함.(직접 부여할 수도 있음)

빈 이름은 중복되면 안 됨!

이후 스프링 빈 의존관계를 설정함.

스프링 빈을 생성하고, 의존 관계를 주입하는 단계가 나누어져 있음.

[컨테이너에 등록된 모든 빈 조회]

스프링 컨테이너에 스프링 빈들이 잘 등록되었는지 확인해보자!

테스트 코드 작성 - ac.getBeanDefinitionNames() : 스프링에 등록된 모든 빈 이름을 조회함

- name = org.springframework.context …. 는 내부적으로 스프링 자체를 확장하기 위해서 쓰는 빈들임. 얘네 제외하고 출력하고 싶으면 ROLE_APPLICATION 사용해서 내가 등록한 혹은 외부 라이브러리 빈만 볼 수 있음! ROLE_INFRASTRUCTURE은 내부에서 사용하는 빈.

[스프링 빈 조회 - 기본]

- ac.getBean(빈 이름, 타입) 으로 조회 가능 (빈 이름 생략 가능)

[스프링 빈 조회 - 동일한 타입이 둘 이상]

같은 타입의 스프링 빈이 둘 이상이면 오류 발생 → 빈 이름 지정해서 해결하기

ac.getBeansOfType() :  해당 타입의 모든 빈 조회 가능

[스프링 빈 조회 - 상속 관계] - 중요!

원칙 - 부모 타입으로 조회하면 자식 타입도 함께 조회함

- ‘Object’ 타입은 모든 객체의 최고 부모이기 때문에 모든 스프링 빈 조회 가능!
- 부모 타입으로 조회 시, 자식이 둘 이상 있으면 빈 이름을 지정해야 중복 오류 피할 수 있음

[BeanFactory와 ApplicationContext]

**BeanFactory**

- 최상위 인터페이스
- 스프링 빈 조회, 관리 역할
- ‘getBean()’ 제공

**ApplicationContext**

- 빈 팩토리를 상속 받는 인터페이스. 빈 팩토리에 부가 기능 더함

  부가 기능

    - MessegeSource : 언어권에 따라 해당 언어로 출력됨 - 파일을 여러 개 분리해놓음
    - EnvironmentCapable : 환경 변수. 로컬 개발 환경, (테스트 서버)개발 환경, 운영 환경 + 스테이징 환경. 각 환경 별로 어떤 데이터베이스 연결할지
    - ApplicationEventPublisher : 이벤트를 발행하고 구독하는 모델을 편리하게 지원
    - ResourceLoader : 외부에서 파일 읽어들여 내부에서 사용할 때 추상화해서 편리하게 사용하도록 지원

[다양한 설정 형식 지원 - 자바 코드, XML]

XML로 설정하기

AnnotationConfigApplicationContext  - 애노테이션 기반 자바 코드 설정 정보 넘기기

GenericXmlApplicationContext - xml 설정 파일 넘기기

[스프링 빈 설정 메타 정보 - BeanDefinition]

스프링이 다양한 설정 형식을 지원할 수 있는 이유 → 다양한 형태의 설정 정보를 BeanDefinition으로 추상화해서 사용함!!

- BeanDefinition 추상화
    - 빈 설정 메타 정보!!  - 스프링 컨테이너가 메타 정보를 기반으로 스프링 빈 생성함
    - 역할과 구현을 개념적으로 나눈 것
    - AnnotationConfigApplicationContext는 AnnotatedBeanDefinitionReader를 사용해서 AppConfig.class를 읽고 BeanDefinition 생성
    - GenericXmlApplicationContext는 XmlBeanDefinitionReader를 사용해서 appConfig.xml을 읽고 BeanDefinition 생성

### Section 6 - 싱글톤 컨테이너

[웹 애플리케이션과 싱글톤]

객체가 자바에 딱 하나만 있어야 한다!

기존 DI 컨테이너 방식 - 고객이 요청할 때마다 객체를 생성함

- memberService1 = hello.core.member.MemberServiceImpl@7c0c77c7
  memberService2 = hello.core.member.MemberServiceImpl@7adda9cc
    - 객체 다르게 생성됨
    - 웹 어플리케이션은 요청이 많음. 객체 계속 만들면 효율성 떨어짐
- 싱글톤 패턴 : 객체는 1개만 생성하고 공유해서 사용함

[싱글톤 패턴]

- 클래스의 인스턴스가 딱 1개만 생성되는 것을 보장하는 디자인 패턴
1. static 영역에 객체 인스턴스 미리 하나 생성해서 올려둠
2. 객체 필요하면 ‘getInstance()’ 메서드를 통해서만 조회할 수 있게 함. (미리 생성해 둔 인스턴스를 가져다 씀)
3. private 생성자 사용 - 외부에서 임의로 new 키워드 사용 못 하게 막음. (똑같은 타입의 객체 2개 이상 생성하지 못 하도록)

singletonService1 = hello.core.singleton.SingletonService@794cb805
singletonService2 = hello.core.singleton.SingletonService@794cb805

싱글톤 적용한 후에는 getInstance()로 조회했을 때 같은 객체가 반환되었다!!

싱글톤 패턴 문제점

- 싱글톤 패턴을 구현하는 코드 자체가 많이 들어감
- 클라이언트가 구체 클래스에 의존해야 함 - DIP, OCP 위반
- private 생성자 사용 - 자식 클래스 만들기 어려움
- 유연성 떨어짐. 안티 패턴으로 불리기도 함

스프링 컨테이너(싱글톤 컨테이너) 사용 시 위의 문제점 다 해결하고!! 기본적으로 객체 싱글톤으로 관리해 줌.

[싱글톤 컨테이너]

- 싱글톤 패턴을 적용하지 않아도 객체 인스턴스를 싱글톤으로 관리함.
- 싱글톤 레지스트리 : 싱글톤 객체를 생성하고 관리하는 기능
- 싱글톤 패턴 구현 코드 필요x → DIP, OCP, private 생성자로부터 자유롭게 싱글톤 사용 가능

(section 5에서 @Bean 호출하고 객체 인스턴스를 미리 생성해서 등록했음)

[싱글톤 방식의 주의점]

- 무상태(stateless)로 설계해야 함
    - 여러 클라이언트가 하나의 객체 인스턴스를 공유하기 때문에 유지(stateful)하게 설계하면 안됨
    - 특정 클라이언트에 의존적인 필드가 있으면 안 됨. 값 변경 x 읽기만 가능하게
    - 필드 대신 자바에서 공유되지 않는 지역변수, 파라미터, ThreadLocal 등을 사용해야 함

[@Configuration과 싱글톤 & 바이트코드 조작의 마법]

@Configuration은 싱글톤을 위해 존재함

AppConfig에서 memberRepository가 3번 호출될 것으로 예상됨.. new 가 3번 실행되는 것 아닐까?

하지만 테스트 결과 memberRepository 한 번만 호출됨

configurationDeep() 테스트 결과 “bean = class hello.core.AppConfig SpringCGLIB0”

- 클래스 명에 CGLIB 붙음 - 스프링이 CGLIB 바이트코드 조작 라이브러리 사용해서 AppConfig 클래스를 상속받은 임의의 다른 클래스를 만들고, 그 클래스를 스프링 빈으로 등록함
- @Bean이 붙은 메서드마다 아래 로직의 코드가 동적으로 생성됨
    - 이미 스프링 빈이 존재하는 경우 - 존재하는 빈 반환
    - 스프링 빈이 없는 경우 - 생성해서 등록하고 반환

@Configuration 없이 @Bean만 적용하는 경우 스프링 빈으로 등록은 되지만 싱글톤 깨짐

- CGLIB없이 AppConfig가 스프링 빈에 등록됨

### Section 7 - 컴포넌트 스캔

[컴포넌트 스캔과 의존관계 자동 주입 시작하기]

스프링 빈 일일이 등록하기 힘듦

- 컴포넌트 스캔 : 설정정보가 없어도 자동으로 스프링 빈을 등록하는 기능
    - @ComponentScan : @Component 붙은 클래스를 전부 자동으로 스프링 빈 등록
        - 스프링 빈 기본 이름 : 클래스 명. 맨 앞글자는 소문자
        - 이름 다르게 지정하고 싶으면 @Component(” 이름 “)
    - excludeFilters - 컴포넌트 스캔 대상에서 설정 정보 제외(기존 예제 코드 유지용)
- @Autowired : 의존관계 자동으로 주입
    - 타입이 같은 빈을 찾아서 주입

[탐색 위치와 기본 스캔 대상]

<탐색할 패키지의 시작 위치 지정하기>

basePackages : 탐색할 패키지의 시작 위치 지정. 시작 패키지의 하위 패키지도 모두 탐색함

basePackageClasses : 지정한 클래스의 패키지를 시작 위치로 지정.

- 시작 위치 지정하지 않으면 @ComponentScan 붙은 위치부터 스캔
- 설정 정보 클래스 위치를 프로젝트 최상단에 두도록 권장하심
- com.gello - 프로젝트 시작 루트. AppConfig 같은 메인 설정 정보 두기
    - @SpringBootApplication 루트에 두는 것이 관례(@ComponentScan 포함됨)
        - 스프링부트 시작 시 자동 생성되는 CoreApplication클래스에 있음

<컴포넌트 스캔 기본 대상>

- @Component
- @Controller
- @Service : 스프링 비즈니스 로직에서 사용
- @Repository : 데이터 접근 계층에서 사용
- @Configuration : 설정 정보에서 사용

[필터]

- includeFilters: 컴포넌트 스캔 대상을 추가로 지정함
- excludeFilters : 제외할 대상을 지정함

FilterType 옵션

-

[중복 등록과 충돌]

컴포넌트 스캔에서 같은 빈 이름을 등록하는 경우