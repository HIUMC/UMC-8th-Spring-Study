package spring.hellospring.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();

    // 항상 같은 인스턴스만 반환
    public static SingletonService getInstance() {
        return instance;
    }

    // 외부에서 객체를 생성하지 못하도록 막는다.
    private SingletonService() {}

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
