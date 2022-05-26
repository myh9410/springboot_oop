package hello.core.discount;

import hello.core.member.Member;

public interface DiscountPolicy {

    /**
     *
     * @return 할인 대상 금액을 반환e
     */
    int discount(Member member, int price);
}
