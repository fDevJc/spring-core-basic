package hello.core.member;

import static org.assertj.core.api.Assertions.*;

import hello.core.AppConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join() {
        //given
        Member member1 = new Member(1L, "Member1", Grade.VIP);

        //when
        memberService.join(member1);
        Member foundMember = memberService.findMember(member1.getId());

        //then
        assertThat(member1).isEqualTo(foundMember);
    }
}
