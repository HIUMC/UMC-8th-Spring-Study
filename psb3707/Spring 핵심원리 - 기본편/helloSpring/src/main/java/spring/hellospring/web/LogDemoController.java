package spring.hellospring.web;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import spring.hellospring.common.MyLogger;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;

    private final MyLogger logger;

//    private final ObjectProvider<MyLogger> myLoggerProvider;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
//        MyLogger logger = myLoggerProvider.getObject();

        String URL = request.getRequestURL().toString();
        logger.setRequestURL(URL);

        logger.log("controller test");
        logDemoService.logic("testId");

        return "success";
    }
}
