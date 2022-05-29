package hello.core.singleton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    @DisplayName("싱글톤 주의할점 테스트")
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA : A사용자가 10000원 주문
        statefulService1.order("userA", 10000);

        //ThreadB : B사용자가 10000원 주문
        statefulService2.order("userB", 20000);

        //ThreadA : 사용자A가 주문 금액을 조회 - 사용자A는 10000원을 주문했지만 실제 금액 조회는 20000원이 나옴
        //statefulService1, statefulService2가 공통된 instance를 사용하기 때문
        int price = statefulService1.getPrice();

        Assertions.assertEquals(statefulService1.getPrice(), 20000);
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}