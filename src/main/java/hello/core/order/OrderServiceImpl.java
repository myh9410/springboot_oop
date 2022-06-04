package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

//    @Autowired private MemberRepository memberRepository; // Field Injection not recommended
//    @Autowired private DiscountPolicy discountPolicy;

    @Autowired
    //생성자 주입 - 생성자 호출시점에 딱 1번만 호출되는 것이 보장된다.
    //불변, 필수 의존 관계에 사용
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        //할인 가격 책정은 discountPolicy에서 진행하여 orderService에서는 관련하여 신경쓸 필요 없음 - 단일 책임 원칙 작 지켜짐
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
