package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class RateDiscountPolicy implements DiscountPolicy {  //까지 적고 압션+엔터 해서 인터페이스 구현하기

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {  //테스트케이스 만드려면 커맨드 쉬프트 티
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
