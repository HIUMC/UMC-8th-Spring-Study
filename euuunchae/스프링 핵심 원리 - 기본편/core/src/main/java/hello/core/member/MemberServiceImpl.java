
package hello.core.member;
//메모리버전 구현체
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member){

    }

    @Override
    public Member findMember(Long memberId){
        return memberRepository.findById(memberId);

    }

}
