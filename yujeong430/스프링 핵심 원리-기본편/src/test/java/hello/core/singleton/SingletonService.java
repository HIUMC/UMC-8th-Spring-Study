package hello.core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();

    // 조회할 때
    public static SingletonService getInstance() {
        return instance;
    }

    // private이므로 외부에서 new로 생성 불가능
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
