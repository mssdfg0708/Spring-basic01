package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.*;
import hello.core.order.*;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository01 = memberService.getMemberRepository();
        MemberRepository memberRepository02 = orderService.getMemberRepository();

        System.out.println("memberRepository01 = " + memberRepository01);
        System.out.println("memberRepository02 = " + memberRepository02);
        System.out.println("memberRepository = " + memberRepository);

        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
    }
}
