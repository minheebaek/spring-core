package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig { //나의 애플리케이션 전체에 대해 설정하고 구성하는 공간

    //멤버서비스에 대한 구현을 나의 애플리케이션에서는 멤버서비스 인플을 쓸거야
    @Bean
    public MemberService memberService() { //AppCofig를 쓸때 멤버서비스 씀
        return new MemberServiceImpl(MemberRepository()); //멤버서비스 구현체인 객체가 생성됨
    }

    //나의 애플리케이션에서는 멤버 리포지토리는 메모리멤버리포지토리로 할거야 -> 나중에 jdbc 리포지토리로 바껴도 이 코드만 바꾸면 된다.
    @Bean
    public MemoryMemberRepository MemberRepository() {
        return new MemoryMemberRepository();
    }

    //오더서비스에 대한 구현을 나의 애플리케이션에서는 오더서비스 인플을 쓸건데 멤버리포지토리는 메모리멤버리포지토리, 디스카운트폴리시는 픽스디스카운트 폴리시 쓸거야
    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(MemberRepository(), discountPolicy()); //AppConfig에서 구현객체를 생성함
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        //return new FixDiscountPolicy();
        //할인클래스를 구체 객체로 바꾸기
        return new RateDiscountPolicy();
    }
}
