# JPA 활용 강의 정리

## 섹션 3 도메인 분석 설계

### 엔티티 설계
엔티티를 설계할때 1대1 관계이면 @OneToOne를 사용하고   
1대다 관계이면 @OneToMany, @ManyToOne를 사용한다   
@ManyToMany는 사용하지 않는게 좋다   
중간 엔티티를 만들고 @OneToMany, @ManyToOne으로 매핑하는것이 좋다   

### 엔티티 설계시 주의점
엔티티에는 가급적 setter를 사용하지 말아야한다   
그리고 모든 연관 관계는 지연로딩으로 설정해야한다   
그리고 컬렉션은 필드에서 바로 초기화해야한다   

## 섹션 5,6 회원,상품 도메인 개발

### 리포지토리 개발
@Repository는 스프링 빈으로 등록된다    
@PersistenceContext는 엔티티 매니저를 주입하고   
@PersistenceUnit은 엔티티 매니저 팩토리를 주입한다   


### 서비스 개발
@Service도 스프링 빈으로 등록된다
@Transactional은 트랜잭션을 시작하고 영속성 컨텍스트를 생성한다   
(readOnly=true) 읽기 전용 메서드에 사용한다   
