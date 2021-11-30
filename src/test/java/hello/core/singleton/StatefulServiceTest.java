package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService01 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService02 = ac.getBean("statefulService", StatefulService.class);

        statefulService01.order("Order01", 10000);
        statefulService02.order("Order02", 20000);

        int price = statefulService01.getPrice();
        System.out.println("price = " + price);

        assertThat(price).isEqualTo(20000);
        assertThat(statefulService01.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}