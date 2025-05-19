package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.repository.MemberMissionRepository.MemberMissionRepository;
import umc.spring.repository.MemberRepository.MemberRepository;
import umc.spring.validation.annotation.IsChallengedMission;

@Component
@RequiredArgsConstructor
public class NotChallengedMissionValidator{
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    public boolean isChllanged(Long missionId, Long memberId) {
        return !memberMissionRepository.existsByMemberIdAndMissionId(memberId, missionId);
    }
}
