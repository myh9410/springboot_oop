package hello.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
//컴퍼넌트 스캔에 추가
public @interface MyIncludeComponent {
}
