package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {  //실제 동작에 필요한 구현 객체를 생성. 앱이 어케 동작할지 전체 구성을 책임짐. 이제 각 배우들은 담당 기능을 '실행'만 하면 됨.

    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());  //의존성 주입(DI)
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());  //생성자 주입(의존성 주입)
    }
}
