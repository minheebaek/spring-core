package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class); //MemberService, 즉 인터페이스로 조회함 그러면 이 인터페이스의 구현체가 대상이 된다
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
        //isInstanceOf() 연산자:해당 타입의 인스턴스인지를 비교하는 메서드, 객체가 특정클래스나 인터페이스로부터 생성된 것인지를 판별
    }

    @Test
    @DisplayName("이름없이 타입으로만 조회")
    void findBeanByType() { //타입으로 조회할때 타입이 여러개일경우 곤란해짐
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
        //이렇게 인터페이스로 조회하면 인터페이스 구현체가 대상이 된다
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2() {
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class); //스프링 컨테이너에 MemberServiceImpl이 등록되어있으면 조회가 됨
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

        /**
         * 구체적인건 좋지 않다
         * 역할과 구현을 구분해야한다. 항상 역할에 의존해야한다.
         * 근데 이건 구현에 의존한게됨
         */
    }

    @Test
    @DisplayName("빈 이름으로 조회X")
    void findBeanNameX() { //항상 성공테스트뿐만 아니라 실패 테스트도 만들어야함
        //MemberService xxxx = ac.getBean("xxxxx", MemberService.class);
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxxx", MemberService.class));  //무조건 이 예외가 터져야 성공한다는 뜻  static import : 알트+엔터

    }
}

