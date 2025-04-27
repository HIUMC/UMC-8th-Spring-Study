package spring.hellospring.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient{

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);

    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect() {
        System.out.println("connect: "  + url);
    }

    public void call(String message) {
        System.out.println("call: " + message);
    }

    public void disconnect() {
        System.out.println("disconnect: "  + url);
    }

    @PostConstruct
    public void init() {
        System.out.println("NetworkClient init...");
        connect();
        call("초기화 호출 메세지");
    }

    @PreDestroy
    public void close() {
        System.out.println("NetworkClient close...");
        disconnect();
    }

//    @Override
//    public void afterPropertiesSet() throws Exception {
//        connect();
//        call("초기화 호출 메세지");
//    }
//
//    @Override
//    public void destroy() throws Exception {
//        disconnect();
//    }
}
