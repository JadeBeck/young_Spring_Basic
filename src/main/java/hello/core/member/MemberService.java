package hello.core.member;

public interface MemberService {

    void join(Member mebmer);

    Member findMember(Long memberId);
}
