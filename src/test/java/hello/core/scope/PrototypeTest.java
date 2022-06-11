package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.junit.jupiter.api.Assertions.assertNotSame;

public class PrototypeTest {

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);
        assertNotSame(prototypeBean1, prototypeBean2);


        // applicationcontext close가 안됨 (destroy 함수가 호출 안됨)
        // prototype이라 스프링 컨테이너에서 생성과 의존관계 주입, 초기화만 관리하고 그 이후에는 관리하지 않는다.
        ac.close();

        // 따라서 직접 destroy 해줘야됨.
        prototypeBean1.destroy();
        prototypeBean2.destroy();
    }

    @Scope("prototype")
    static class PrototypeBean {
            @PostConstruct
            public void init() {
                System.out.println("SingletonBean.init");
            }

            @PreDestroy
            public void destroy() {
                System.out.println("SingletonBean.destroy");
            }
    }
}
