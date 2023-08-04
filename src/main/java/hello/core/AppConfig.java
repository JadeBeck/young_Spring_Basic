package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    //실제 동작에 필요한 구현 객체를 생성. 앱이 어케 동작할지 전체 구성을 책임짐. 이제 각 배우들은 담당 기능을 '실행'만 하면 됨.
    //역할과 구현 클래스를 한눈에 볼 수 있음.

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());  //의존성 주입(DI)
    }

    @Bean
    public static MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), new FixDiscountPolicy());  //생성자 주입(의존성 주입)
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
