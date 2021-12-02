package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


public class SingletonTest {

    @Test
    void singletonBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);

        SingletonBean singletonBean01 = ac.getBean(SingletonBean.class);
        SingletonBean singletonBean02 = ac.getBean(SingletonBean.class);
        System.out.println("singletonBean01 = " + singletonBean01);
        System.out.println("singletonBean02 = " + singletonBean02);
        assertThat(singletonBean01).isSameAs(singletonBean02);

        ac.close();
    }

    @Scope("singleton")
    static class SingletonBean {

        @PostConstruct
        public void init() {
            System.out.println("SingletonBean.init");
        }

        @PreDestroy
        public void destroy () {
            System.out.println("SingletonBean.destroy");
        }
    }
}
