package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

    MemberService memberServic;

    @BeforeEach
    public void beforEach(){
        AppConfig appConfig=new AppConfig();
        memberServic= appConfig.memberService();
    }
}



