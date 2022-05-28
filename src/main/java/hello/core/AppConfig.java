package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {

    //역할을 드러나게 리팩토링

    public MemberService memberService() {
        //생성자를 통해 주입 (injection)
        return new MemberServiceImpl(memberRepository());
    }

    private MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService OrderService() {
        //생성자를 통해 주입 (injection)
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy() {
        //할인 정책이 변경된다면 이 부분만 수정해주면 된다. - 사용 영역은 수정하지 않고, 구성 영역만 수정
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
