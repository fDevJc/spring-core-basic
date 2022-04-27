package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {

    @Test
    @DisplayName("AutoAppConfigTest")
    void basicScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        //기본 스프링의 빈이름은 구현체의 클래스이름으로 만들어진다 ex) memberServiceImpl
        //이름을 따로 지정하고 싶다면 @Component("memberService")
//        MemberService memberService = ac.getBean("memberServiceImpl", MemberServiceImpl.class);

        MemberService memberService = ac.getBean(MemberService.class);

        System.out.println("memberService = " + memberService);

        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
