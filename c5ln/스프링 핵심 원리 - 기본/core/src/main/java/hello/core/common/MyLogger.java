package hello.core.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {

    private String uuid;
    private String requestId;

    public void setRequestURL(String requestId) {
        this.requestId = requestId;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "] " + "[" + requestId + "] " + message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] " + "request scope bean create: " + this);

    }

    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] " + "request scope bean close: " + this);
    }
}
