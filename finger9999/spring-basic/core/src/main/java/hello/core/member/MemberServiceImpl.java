package hello.core.member;

public class MemberServiceImpl implements MemberService {

    //생성자 주입할 수 있도록
    private final MemberRepository memberRepository;

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

    //생성자를 통해 memberRepository에 뭐가 들어갈지 정함
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }


}
