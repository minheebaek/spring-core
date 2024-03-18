package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    /* 추상에만 의존해야하는데 구체적인것도 의존해버림 (OCP 위반,DIP 위반) - 해결방법:구체 지워버리기
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    */
    private DiscountPolicy discountPolicy; //구체에 의존하지 않고 추상화에만 의존함 이러면 구체 객체를 만들지 않아서 널포인트익셉션 생김

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice); //최종 생성된 주문 반환
    }
}
