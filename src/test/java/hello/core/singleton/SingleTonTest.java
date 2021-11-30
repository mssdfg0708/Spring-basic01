package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class SingleTonTest {

    @Test
    @DisplayName("DI Container Without Spring")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        MemberService memberService01 = appConfig.memberService();
        MemberService memberService02 = appConfig.memberService();

        System.out.println("memberService01 = " + memberService01);
        System.out.println("memberService02 = " + memberService02);

        assertThat(memberService01).isNotSameAs(memberService02);
    }

    @Test
    @DisplayName("Spring Container")
    void springContainer() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService01 = ac.getBean("memberService", MemberService.class);
        MemberService memberService02 = ac.getBean("memberService", MemberService.class);

        System.out.println("memberService01 = " + memberService01);
        System.out.println("memberService02 = " + memberService02);

        assertThat(memberService01).isSameAs(memberService02);
    }

    @Test
    void ConfigurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        AppConfig bean = ac.getBean(AppConfig.class);
        System.out.println("bean.getClass() = " + bean.getClass());
    }
}
