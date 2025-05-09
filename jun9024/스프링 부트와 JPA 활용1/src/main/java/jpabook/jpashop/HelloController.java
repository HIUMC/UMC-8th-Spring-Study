package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController{

    @GetMapping("hello") //hello라는 url이오면 이 controller가 실행됨.
    public String hello(Model model){ //model에 데이터를 실어서 controller에서 데이터를 뷰에 넘김.
        model.addAttribute("data", "hello!!");
        return "hello"; //화면 이름(hello.html로 자동으로 넘어감.)
    }
}
