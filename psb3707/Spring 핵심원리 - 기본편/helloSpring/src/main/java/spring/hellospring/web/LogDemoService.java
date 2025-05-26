package spring.hellospring.web;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;
import spring.hellospring.common.MyLogger;

@Service
@RequiredArgsConstructor
public class LogDemoService {
    private final MyLogger logger;

//    private final ObjectProvider<MyLogger> myLoggerProvider;

    public void logic(String id) {
//        MyLogger logger = myLoggerProvider.getObject();
        logger.log("service id = " + id);
    }
}
