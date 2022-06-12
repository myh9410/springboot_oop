package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
//ProxyMode : Class면 TARGET_CLASS, Interface면 INTERFACE로
//CGLIB이라는 라이브러리로 가짜 프록시 객체를 만들어서 주입한다. (가짜 프록시 객체는 싱글톤처럼 사용)
//가짜 객체가 myLogger라는 이름으로 스프링 컨테이너에 등록된다. -> ac.getBean으로도 proxy 객체가 조회됨.
//프록시빈은 실제 요청이 왔을 때, 진짜 빈을 요청하는 로직이 들어있다.
//진짜 객체 조회를 필요한 시점까지 "지연처리"

@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create:" + this);
    }

    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close:" + this);
    }
}
