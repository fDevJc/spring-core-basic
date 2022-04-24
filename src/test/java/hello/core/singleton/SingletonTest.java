package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        //1. 조회 : 호출할 떄 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();
        //2. 조회 : 호출할 떄 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        Assertions.assertThat(memberService1).isNotEqualTo(memberService2);
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);

        /**
         * 스프링 없는 순수한 DI컨테이너인 AppConfig는 요청을 할때마다 객체를 새로 생성
         * 그러므로 메모리 낭비가 심하다
         * 객체가 하나만 생성되고 공유되게 만들면된다 -> 싱글톤패턴
         */

    }
}
