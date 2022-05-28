package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        Assertions.assertInstanceOf(MemberServiceImpl.class, memberService);
    }

    @Test
    @DisplayName("빈 이름없이 타입으로만 조회")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertInstanceOf(MemberServiceImpl.class, memberService);
    }

    @Test
    @DisplayName("구현체 타입으로 조회") //역할과 구현을 구분하지 않았기에 좋은 방법은 아니지만, 어쩌다 필요할 떄가 있을것
    void findBeanByName2() {
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        Assertions.assertInstanceOf(MemberServiceImpl.class, memberService);
    }

    @Test
    @DisplayName("빈 이름으로 조회 실패")
    void findBeanByNameFail() {
//        ac.getBean("xxxxx", MemberService.class); //NoSuchBeanDefinitionException

        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("xxxxx", MemberService.class));
    }

}
