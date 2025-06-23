package umc.spring.service.MemberMissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.MemberMissionHandler;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository.MemberMissionRepository;

@Service
@RequiredArgsConstructor
public class MemberMissionQueryServiceImpl implements MemberMissionQueryService {

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public Page<MemberMission> getMissionList(Long memberId, Integer page) {
        Pageable pageable = PageRequest.of(page, 10); // 페이지당 10개
        return memberMissionRepository.findAllByMemberIdAndStatus(memberId, MissionStatus.CHALLENGING, pageable);
    }

    @Override
    public void completeMission(Long memberId, Long missionId) {
        MemberMission memberMission = memberMissionRepository
                .findByMemberIdAndMissionIdAndStatus(memberId, missionId, MissionStatus.CHALLENGING)
                .orElseThrow(() -> new MemberMissionHandler(ErrorStatus.MEMBER_MISSION_NOT_CHALLENGING));

        memberMission.setStatus(MissionStatus.COMPLETE);
    }
}
