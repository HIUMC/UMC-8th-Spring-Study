package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;


import java.util.List;
import java.util.Optional;

public class MemberService{
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository=memberRepository;
    }

    public Long koin(Member member){

        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()) // 여기 수정
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다."); // IllegalAccessException → IllegalStateException으로 변경
                });
    }


    public List<Member> findMembers(){return memberRepository.findAll();}

    public Optional<Member> findOne(Long memberId){return memberRepository.findById(memberId);}

}

