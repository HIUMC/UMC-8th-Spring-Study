package hello.core.member;
//메모리버전 구현체
public class MemberServiceimpl implements MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member){

    }

    @Override
    public Member findMember(Long memberId){
        return memberRepository.findById(memberId)  ;

    }

}
