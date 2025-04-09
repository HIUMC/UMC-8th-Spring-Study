package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//클라이언트
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
//memberRepository에 MemoryMemberRepository를 넣어줌.(AppConfig에서 스프링 빈으로 등록)


    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void join(Member member) {
        memberRepository.save(member);
    }

    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }


    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}