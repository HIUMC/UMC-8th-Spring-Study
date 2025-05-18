package umc.spring.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Member;
import umc.spring.repository.*;
import umc.spring.repository.MemberMissionRepository.MemberMissionRepository;
import umc.spring.repository.MemberRepository.MemberRepository;
import umc.spring.repository.ReviewRepository.ReviewRepository;

@Service
@RequiredArgsConstructor
public class MemberService {
        private final MemberRepository memberRepository;
        private final MemberMissionRepository memberMissionRepository;
        private final MemberAgreeRepository memberAgreeRepository;
        private final MemberPreferRepository memberPreferRepository;
        private final ReviewRepository reviewRepository;


    @Transactional
    public void deleteMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new IllegalArgumentException("Invalid member id: " + memberId));

        memberPreferRepository.deleteAllByMemberId(memberId);
        memberAgreeRepository.deleteAllByMemberId(memberId);
        memberMissionRepository.deleteAllByMemberId(memberId);
        reviewRepository.deleteAllByMemberId(memberId);

        member.inactivate();
    }

}
