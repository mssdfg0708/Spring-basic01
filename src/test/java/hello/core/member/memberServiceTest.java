package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class memberServiceTest {

    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void signUp(){
        Member member = new Member(1L, "Leezhe", Grade.VIP);
        memberService.signUp(member);
        Member findMember = memberService.findMember(1L);

        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
