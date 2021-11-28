package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class memberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    void signUp(){
        Member member = new Member(1L, "Leezhe", Grade.VIP);
        memberService.signUp(member);
        Member findMember = memberService.findMember(1L);

        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
