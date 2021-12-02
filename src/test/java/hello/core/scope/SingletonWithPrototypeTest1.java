package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean bean01 = ac.getBean(PrototypeBean.class);
        bean01.addCount();
        assertThat(bean01.getCount()).isEqualTo(1);

        PrototypeBean bean02 = ac.getBean(PrototypeBean.class);
        bean02.addCount();
        assertThat(bean02.getCount()).isEqualTo(1);
    }

    @Test
    void SingletonClient() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean client01 = ac.getBean(ClientBean.class);
        int count01 = client01.logic();
        assertThat(count01).isEqualTo(1);

        ClientBean client02 = ac.getBean(ClientBean.class);
        int count02 = client02.logic();
        assertThat(count02).isEqualTo(2);
    }

    @Test
    void providerClient() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(provider.class, PrototypeBean.class);

        provider client01 = ac.getBean(provider.class);
        int count01 = client01.logic();
        assertThat(count01).isEqualTo(1);

        provider client02 = ac.getBean(provider.class);
        int count02 = client02.logic();
        assertThat(count02).isEqualTo(1);
    }

    static class ClientBean {
        private final PrototypeBean prototypeBean;

        public ClientBean(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }

        int logic() {
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    static class provider {
        private final ObjectProvider<PrototypeBean> prototypeBeanProvider;

        public provider(ObjectProvider<PrototypeBean> prototypeBeanProvider) {
            this.prototypeBeanProvider = prototypeBeanProvider;
        }

        int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }

    }

    @Scope("prototype")
    static class PrototypeBean {

        private int count = 0;

        public void addCount() {
            count += 1;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
