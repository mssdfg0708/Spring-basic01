package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }
    static class TestBean {

        @Autowired(required = false)
        public void SetNoBean01(Member member) {
            System.out.println("SetNoBean01 = " + member);
        }

        @Autowired
        public void setNoBean02(@Nullable Member member) {
            System.out.println("setNoBean02 = " + member);
        }

        @Autowired
        public void setNoBean03(Optional<Member> member) {
            System.out.println("setNoBean03 = " + member);
        }
    }
}
