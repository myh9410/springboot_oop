package hello.core;

import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "hello.core", //탐색 시작할 위치 지정
        //basePackageClasses = AutoAppConfig.class 해당 클래스의 패키지 위치
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {


    @Bean(name = "memoryMemberRepository")
    //기존에는 수동 빈 등록 - 자동 빈 등록 이름이 같다면 override한다
    //최근 스프링은 spring.main.allow-bean-definition-overriding=true 인경우만 override, false이면 에러 발생하도록 변경됨
    MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
